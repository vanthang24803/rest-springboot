package com.example.project.services.ipml;

import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Category;
import com.example.project.models.Product;
import com.example.project.repositories.CategoryRepository;
import com.example.project.repositories.ProductRepository;
import com.example.project.services.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceIpml implements ProductCategoryService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public boolean addCategoryToProduct(UUID productId, UUID categoryId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.getCategories().add(category);
        productRepository.save(product);

        return true;
    }

    @Override
    public boolean removeCategoryFromProduct(UUID productId, UUID categoryId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        product.getCategories().remove(category);
        productRepository.save(product);

        return true;
    }
}
