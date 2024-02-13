package com.example.project.services;

import com.example.project.dtos.request.CreateProductDto;
import com.example.project.models.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product save(CreateProductDto createProductDto);

    List<Product> findAll();

    Optional<Product> findById(UUID id);

    void delete(UUID id);

    boolean isExists(UUID id);


    Product update(UUID id , CreateProductDto createProductDto);
}
