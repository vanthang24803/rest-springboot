package com.example.project.services;

import java.util.UUID;

public interface ProductCategoryService {
    boolean addCategoryToProduct(UUID productId, UUID categoryId);

    boolean removeCategoryFromProduct(UUID productId, UUID categoryId);
}
