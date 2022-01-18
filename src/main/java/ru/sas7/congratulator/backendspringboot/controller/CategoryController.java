package ru.sas7.congratulator.backendspringboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sas7.congratulator.backendspringboot.entity.Category;
import ru.sas7.congratulator.backendspringboot.repo.CategoryRepository;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("")
    public List<Category> test() {
        List<Category> list = categoryRepository.findAll();
        System.out.println("List = " + list);
        return list;
    }
}
