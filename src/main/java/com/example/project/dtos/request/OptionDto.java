package com.example.project.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptionDto {
    @NotEmpty(message = "Email is required")
    private String name;

    @Min(0)
    @Positive(message = "Sale must be a positive number")
    private int sale;

    @Min(0)
    @Positive(message = "Quantity must be a positive number")
    private int quantity;

    @Min(0)
    @Positive(message = "Price must be a positive number")
    private double price;
}
