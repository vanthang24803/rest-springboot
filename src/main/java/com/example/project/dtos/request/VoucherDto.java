package com.example.project.dtos.request;


import com.example.project.enums.Coupon;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class VoucherDto {
    private String name;

    private String title;

    private int quantity;

    private int day;

    private int discount;

    @Enumerated(EnumType.STRING)
    private Coupon coupon;

    private LocalDateTime shelfLife;
}
