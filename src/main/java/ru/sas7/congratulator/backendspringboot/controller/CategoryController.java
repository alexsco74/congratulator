package ru.sas7.congratulator.backendspringboot.controller;

import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sas7.congratulator.backendspringboot.entity.Category;
import ru.sas7.congratulator.backendspringboot.repo.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public List<Category> getCategories() {
        List<Category> list = categoryRepository.findAll();
        System.out.println("List = " + list);
        return list;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {

        // Проверяем чтобы не было id
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("Неверный параметр: в объекте не должно быть параметра id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Проверяем чтобы было name
        if (category.getName() == null || category.getName().trim().length() == 0) {
            return new ResponseEntity("Неверный параметр: name обязательный параметр", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryRepository.save(category));
    }
}
