package com.example.project.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CouponDto {

    @NotEmpty(message = "Code is required")
    private String code;
}
