package com.example.project.services.ipml;

import com.example.project.dtos.request.CategoryDto;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Category;
import com.example.project.repositories.CategoryRepository;
import com.example.project.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryServiceIpml implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category save(CategoryDto categoryDto) {
        Category category = mapToDto(categoryDto);
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(categoryRepository.findAll());
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category update(UUID id, CategoryDto categoryDto) {
        return categoryRepository.findById(id).map(
                exittingCategory -> {
                    Optional.ofNullable(categoryDto.getName()).ifPresent(exittingCategory::setName);
                    return categoryRepository.save(exittingCategory);
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }

    @Override
    public boolean isExists(UUID id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    private Category mapToDto(CategoryDto categoryDto) {
        return Category.builder()
                .name(categoryDto.getName())
                .build();
    }
}
