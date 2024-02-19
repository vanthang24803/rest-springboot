package com.example.project.services;

import com.example.project.dtos.request.CreateProductDto;
import com.example.project.models.Product;
import com.example.project.untils.QueryObject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product save(CreateProductDto createProductDto);

    List<Product> findAll(QueryObject queryObject);

    Optional<Product> findById(UUID id);

    void delete(UUID id);

    boolean isExists(UUID id);


    Product update(UUID id , CreateProductDto createProductDto);
}
