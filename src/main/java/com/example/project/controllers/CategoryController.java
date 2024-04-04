package com.example.project.controllers;

import com.example.project.dtos.request.CategoryDto;
import com.example.project.models.Category;
import com.example.project.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping(path = "category")
    public ResponseEntity<?> createCategory(@RequestBody @Valid CategoryDto categoryDto) {
        Category category = categoryService.save(categoryDto);
        return new ResponseEntity<>(category, HttpStatus.CREATED);
    }

    @GetMapping(path = "categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping(path = "category/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable UUID id) {
        return categoryService.findById(id)
                .map(category -> new ResponseEntity<>(category, HttpStatus.OK))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    @PutMapping(path = "category/{id}")
    public ResponseEntity<?> updateCategory(
            @PathVariable UUID id, @RequestBody @Valid CategoryDto categoryDto
    ) {
        if (!categoryService.isExists(id)) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(categoryService.update(id, categoryDto), HttpStatus.OK);
    }

    @DeleteMapping(path = "category/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable UUID id
    ) {
        if (!categoryService.isExists(id)) {
            return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
        }

        categoryService.delete(id);

        return new ResponseEntity<>("Category deleted successfully", HttpStatus.OK);
    }
}
