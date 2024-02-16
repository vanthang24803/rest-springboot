package com.example.project.services;


import com.example.project.dtos.response.MessageResponseDto;
import com.example.project.dtos.response.RoleDto;
import com.example.project.models.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    Role save(RoleDto roleDto);

    Role update(UUID id , RoleDto roleDto);

    boolean isExist(UUID id);

    List<Role> findAll();

    void delete(UUID id);

    void deleteRoleOfProduct(UUID id , String email);

    MessageResponseDto upgradeToAdmin(String email);
    MessageResponseDto upgradeToManager(String email);

}
