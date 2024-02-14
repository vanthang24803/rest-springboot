package com.example.project.dtos.request;

import lombok.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    private String name;

    private int sale;

    private int quantity;

    private double price;
}
