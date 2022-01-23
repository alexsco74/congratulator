package ru.sas7.congratulator.backendspringboot.service;

import org.springframework.stereotype.Service;
import ru.sas7.congratulator.backendspringboot.entity.Category;
import ru.sas7.congratulator.backendspringboot.repo.CategoryRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAllByOrderByNameAsc();
    }

    public Category add(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findByParams(String name) {
        return categoryRepository.findByParams(name);
    }
}
