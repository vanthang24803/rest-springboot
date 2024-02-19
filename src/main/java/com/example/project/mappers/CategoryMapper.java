package com.example.project.mappers;

import com.example.project.dtos.request.CategoryDto;
import com.example.project.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record CategoryMapper(ModelMapper modelMapper) {
    public Category mapToDto(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }
}
