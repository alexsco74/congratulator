package ru.sas7.congratulator.backendspringboot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sas7.congratulator.backendspringboot.entity.Category;

// абстракция-реализация
@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
