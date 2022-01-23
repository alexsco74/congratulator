package ru.sas7.congratulator.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.sas7.congratulator.backendspringboot.entity.Category;

import java.util.List;

// абстракция-реализация
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("SELECT c FROM Category c WHERE " +
            "(:name is null or :name='' or lower(c.name) like lower(concat('%', :name, '%'))) " +
            "order by c.name asc")
    List<Category> findByParams(@Param("name") String name);

    // Метод для получения списка категорий с сортировкой по имени
    List<Category> findAllByOrderByNameAsc();
}
