package com.example.project.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {
    private String name;
    private String brand;
    private String thumbnail;

    private UUID categoryId;

    List<OptionDto> options;

}
