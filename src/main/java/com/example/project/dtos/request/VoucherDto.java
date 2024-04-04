package com.example.project.dtos.request;


import com.example.project.enums.Coupon;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoucherDto {
    @NotEmpty(message = "Name is required")
    private String name;

    @NotEmpty(message = "Title is required")
    private String title;

    @Positive(message = "Quantity must be a positive number")
    @Min(0)
    private int quantity;

    @Positive(message = "Day must be a positive number")
    @Min(0)
    private int day;

    @Positive(message = "Discount must be a positive number")
    @Min(0)
    private int discount;

    @Enumerated(EnumType.STRING)
    private Coupon coupon;

    @NotNull(message = "Shelf life is required")
    @PastOrPresent(message = "Shelf life must be in the past or present")
    private LocalDateTime shelfLife;
}
