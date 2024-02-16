package com.example.project.services;

import com.example.project.dtos.request.PasswordDto;
import com.example.project.dtos.request.UpdateProfileDto;
import com.example.project.dtos.response.MessageResponseDto;
import com.example.project.dtos.response.ProfileDto;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    ProfileDto getProfile(String email);

    ProfileDto update(String email , UpdateProfileDto updateProfileDto);

    ProfileDto avatar(String email , MultipartFile file);

    MessageResponseDto updatePassword(String email , PasswordDto passwordDto);
}
