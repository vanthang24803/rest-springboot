package com.example.project.dtos.response;

import com.example.project.models.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String avatar;

    List<RoleDto> roles = new ArrayList<>();

    List<Address> addresses = new ArrayList<>();
}
