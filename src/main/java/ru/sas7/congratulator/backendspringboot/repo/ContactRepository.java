package ru.sas7.congratulator.backendspringboot.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sas7.congratulator.backendspringboot.entity.Contact;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

// абстракция-реализация
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    @Query("SELECT n FROM Contact n WHERE " +
            "(:firstName is null OR :firstName='' OR lower(n.firstName) like lower(concat('%', :firstName, '%'))) AND " +
            "(:middleName is null OR :middleName='' OR lower(n.middleName) like lower(concat('%', :middleName, '%')))" +
            " AND " +
            "(:lastName is null OR :lastName='' OR lower(n.lastName) like lower(concat('%', :lastName, '%'))) AND " +
            "(:categoryId is null OR n.category.id=:categoryId)"
    )
    List<Contact> findByParams(@Param("firstName") String firstName,
                               @Param("middleName") String middleName,
                               @Param("lastName") String lastName,
                               @Param("categoryId") Integer categoryId);

    // Получить ближайшие дни рождения постронично
    @Query("SELECT n FROM Contact n WHERE" +
            " (:categoryId is null OR n.category.id=:categoryId)" +
            " AND ((month(n.birthday) = month(:nowDate) AND day(n.birthday) >= day(:nowDate))" +
            " OR (month(n.birthday) > month(:nowDate)))" +
            " ORDER BY month(n.birthday), day(n.birthday)")
    Page<Contact> findUpcomingBirthdays(@Param("nowDate") LocalDate nowDate,
                                        @Param("categoryId") Integer categoryId,
                                        Pageable pageable);

    // Получить список контактов с сортировкой по дню рождения
    List<Contact> findAllByOrderByLastNameAsc();
}
