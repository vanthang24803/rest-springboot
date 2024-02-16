package com.example.project.controllers;

import com.example.project.dtos.request.PasswordDto;
import com.example.project.dtos.request.UpdateProfileDto;
import com.example.project.dtos.response.MessageResponseDto;
import com.example.project.dtos.response.ProfileDto;
import com.example.project.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping(path = "profile")
    public ResponseEntity<?> profile(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        ProfileDto profile = profileService.getProfile(email);

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping(path = "profile")
    public ResponseEntity<?> update(
            Principal principal, @RequestBody UpdateProfileDto updateProfileDto) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        ProfileDto profile = profileService.update(email, updateProfileDto);
        return new ResponseEntity<>(profile, HttpStatus.OK);
    }

    @PutMapping(path = "profile/avatar")
    public ResponseEntity<?> avatar(
            Principal principal, @RequestParam("file") MultipartFile file) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        ProfileDto result = profileService.avatar(email, file);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path = "profile/password")
    public ResponseEntity<?> password(
            Principal principal, @RequestBody PasswordDto passwordDto) {

        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        MessageResponseDto message = profileService.updatePassword(email, passwordDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
