package com.example.project.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ForgotPasswordDto {
    @NotEmpty(message = "Email is required")
    private String email;
}
