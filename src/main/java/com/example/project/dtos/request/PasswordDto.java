package com.example.project.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordDto {
    @NotEmpty(message = "Old password is required")
    private String oldPassword;
    @NotEmpty(message = "New Password is required")
    private String newPassword;
}
