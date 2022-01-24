package ru.sas7.congratulator.backendspringboot.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sas7.congratulator.backendspringboot.entity.Category;
import ru.sas7.congratulator.backendspringboot.search.CategorySearchParams;
import ru.sas7.congratulator.backendspringboot.service.CategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public List<Category> getCategories() {
        List<Category> list = categoryService.getCategories();
        System.out.println("List = " + list);
        return list;
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {

        // Проверяем чтобы не было id
        if (category.getId() != null && category.getId() != 0) {
            return new ResponseEntity("Неверный параметр: в объекте не должно быть параметра id", HttpStatus.NOT_ACCEPTABLE);
        }

        // Проверяем чтобы было name
        if (category.getName() == null || category.getName().trim().length() == 0) {
            return new ResponseEntity("Неверный параметр: name обязательный параметр", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.add(category));
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        // Проверяем чтобы было id
        if (category.getId() == null || category.getId() == 0) {
            return new ResponseEntity("Неверный параметр: id обязательный параметр", HttpStatus.NOT_ACCEPTABLE);
        }

        // Проверяем чтобы было name
        if (category.getName() == null || category.getName().trim().length() == 0) {
            return new ResponseEntity("Неверный параметр: name обязательный параметр", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(categoryService.update(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable int id) {
        Category category;

        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return new ResponseEntity("Категория с id: " + id + " не найдена", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable int id) {

        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
            return new ResponseEntity("Категория с id: " + id + " не найдена.", HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    // Поиск по параметрам
    @GetMapping("/search")
    public ResponseEntity<List<Category>> findByParams(@RequestBody CategorySearchParams categorySearchParams) {
        return ResponseEntity.ok(categoryService.findByParams(categorySearchParams.getName()));
    }
}
