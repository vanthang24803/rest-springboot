package com.example.project.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDto {

    private String productId;

    private String optionId;

    private String name;

    private String thumbnail;

    private String option;

    public double price;

    public int sale;

    public int quantity;
}
