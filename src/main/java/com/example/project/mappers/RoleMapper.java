package com.example.project.mappers;

import com.example.project.dtos.response.RoleDto;
import com.example.project.models.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record RoleMapper(ModelMapper modelMapper) {
    public Role mapToDto(RoleDto roleDto) {
        return modelMapper.map(roleDto, Role.class);
    }
}
