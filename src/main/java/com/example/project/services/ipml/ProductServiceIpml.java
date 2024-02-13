package com.example.project.services.ipml;

import com.example.project.dtos.request.CreateProductDto;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Product;
import com.example.project.repositories.ProductRepository;
import com.example.project.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductServiceIpml implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product save(CreateProductDto createProductDto) {
        Product product = mapToCreate(createProductDto);
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productRepository
                .findAll());
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public boolean isExists(UUID id) {
        return productRepository.existsById(id);
    }


    @Override
    public Product update(UUID id, CreateProductDto createProductDto) {
        return productRepository.findById(id).map(
                exittingProduct -> {
                    Optional.ofNullable(createProductDto.getName()).ifPresent(exittingProduct::setName);
                    Optional.ofNullable(createProductDto.getBrand()).ifPresent(exittingProduct::setBrand);
                    Optional.ofNullable(createProductDto.getThumbnail()).ifPresent(exittingProduct::setThumbnail);
                    return productRepository.save(exittingProduct);
                }).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    }


    private Product mapToCreate(CreateProductDto createProductDto) {
        return Product.builder()
                .name(createProductDto.getName())
                .brand(createProductDto.getBrand())
                .thumbnail(createProductDto.getThumbnail())
                .build();
    }
}
