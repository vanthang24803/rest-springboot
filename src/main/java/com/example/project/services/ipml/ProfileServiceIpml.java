package com.example.project.services.ipml;

import com.example.project.dtos.request.PasswordDto;
import com.example.project.dtos.request.UpdateProfileDto;
import com.example.project.dtos.response.MessageResponseDto;
import com.example.project.dtos.response.ProfileDto;
import com.example.project.dtos.response.RoleDto;
import com.example.project.models.UserEntity;
import com.example.project.repositories.UserRepository;
import com.example.project.services.ProfileService;
import com.example.project.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceIpml implements ProfileService {
    private final UserRepository userRepository;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ProfileDto getProfile(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return mapFromDto(user);
    }

    @Override
    public ProfileDto update(String email, UpdateProfileDto updateProfileDto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setEmail(updateProfileDto.getEmail());
        user.setFirstName(updateProfileDto.getFirstName());
        user.setLastName(updateProfileDto.getLastName());

        userRepository.save(user);

        return mapFromDto(user);
    }

    @Override
    public ProfileDto avatar(String email, MultipartFile file) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            String url = uploadService.uploadFile(file);
            user.setAvatar(url);

            userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapFromDto(user);
    }

    @Override
    public MessageResponseDto updatePassword(String email, PasswordDto passwordDto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(passwordDto.getOldPassword(), user.getPassword())) {
            return new MessageResponseDto(false, "Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(passwordDto.getNewPassword()));
        userRepository.save(user);


        return  new MessageResponseDto(true, "Password updated successfully");
    }

    public ProfileDto mapFromDto(UserEntity userEntity) {
        ProfileDto profileDto = new ProfileDto();

        profileDto.setId(userEntity.getId());
        profileDto.setFirstName(userEntity.getFirstName());
        profileDto.setLastName(userEntity.getLastName());
        profileDto.setEmail(userEntity.getEmail());
        profileDto.setAvatar(userEntity.getAvatar());

        List<RoleDto> roleDtos = userEntity.getRoles().stream()
                .map(role -> new RoleDto(role.getId(), role.getName()))
                .collect(Collectors.toList());
        profileDto.setRoles(roleDtos);

        profileDto.setAddresses(new ArrayList<>(userEntity.getAddresses()));

        return profileDto;
    }

}
