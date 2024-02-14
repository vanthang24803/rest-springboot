package com.example.project.services;

import com.example.project.dtos.request.CategoryDto;
import com.example.project.models.Category;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryService {
    Category save(CategoryDto categoryDto);

    List<Category> findAll();

    Optional<Category> findById(UUID id);

    Category update(UUID id , CategoryDto categoryDto);

    boolean isExists(UUID id);

    void  delete(UUID id);
}
