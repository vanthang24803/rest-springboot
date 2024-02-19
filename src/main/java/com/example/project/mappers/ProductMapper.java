package com.example.project.mappers;

import com.example.project.dtos.request.CreateProductDto;
import com.example.project.models.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record ProductMapper(ModelMapper modelMapper) {
    public Product mapToCreate(CreateProductDto createProductDto) {
        return modelMapper.map(createProductDto, Product.class);
    }

}
