package ru.sas7.congratulator.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sas7.congratulator.backendspringboot.entity.Contact;

// абстракция-реализация
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {
}
