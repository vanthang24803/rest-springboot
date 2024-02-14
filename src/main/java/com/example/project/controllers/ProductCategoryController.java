package com.example.project.controllers;

import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.services.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductCategoryController {
    private final ProductCategoryService productCategoryService;

    @PostMapping(path = "/{productId}/category/{categoryId}")
    public ResponseEntity<?> addCategoryToProduct
            (@PathVariable UUID productId, @PathVariable UUID categoryId) {
        try {
            boolean result = productCategoryService.addCategoryToProduct(productId, categoryId);
            if (result) {
                return ResponseEntity.ok("Category added to product successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add category to product");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{productId}/category/{categoryId}")
    public ResponseEntity<?> removeCategoryFromProduct
            (@PathVariable UUID productId, @PathVariable UUID categoryId) {
        try {
            boolean result = productCategoryService.removeCategoryFromProduct(productId, categoryId);
            if (result) {
                return ResponseEntity.ok("Category removed to product successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add category to product");
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
