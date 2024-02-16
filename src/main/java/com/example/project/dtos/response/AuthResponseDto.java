package com.example.project.dtos.response;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String email;
    private String accessToken;
}
