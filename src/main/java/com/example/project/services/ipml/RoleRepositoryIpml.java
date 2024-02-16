package com.example.project.services.ipml;

import com.example.project.dtos.response.MessageResponseDto;
import com.example.project.dtos.response.RoleDto;
import com.example.project.models.Role;
import com.example.project.models.UserEntity;
import com.example.project.repositories.RoleRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.expression.ExpressionException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleRepositoryIpml implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Role save(RoleDto roleDto) {
        Role role = mapToDto(roleDto);

        return roleRepository.save(role);
    }

    @Override
    public Role update(UUID id, RoleDto roleDto) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        role.setName(roleDto.getName());

        return roleRepository.save(role);
    }

    @Override
    public boolean isExist(UUID id) {
        return roleRepository.existsById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found"));
        roleRepository.delete(role);
    }

    @Override
    public void deleteRoleOfProduct(UUID id, String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new ExpressionException("Role not found"));

        user.getRoles().remove(role);
        userRepository.save(user);
    }

    @Override
    public MessageResponseDto upgradeToAdmin(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        boolean hasManagerRole = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("MANAGER"));
        boolean hasAdminRole = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ADMIN"));

        if (hasAdminRole) {
            return new MessageResponseDto(false, "Your role is admin");
        } else if (hasManagerRole) {
            Role admin = roleRepository.findByName("ADMIN")
                    .orElseThrow(() -> new ExpressionException("Role not found"));
            user.getRoles().add(admin);
            userRepository.save(user);
            return new MessageResponseDto(true, "Update role successfully !");
        } else {
            return new MessageResponseDto(false, "Unauthorized");
        }
    }


    @Override
    public MessageResponseDto upgradeToManager(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean isAlreadyManager = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("MANAGER"));

        if (isAlreadyManager) {
            return new MessageResponseDto(false, "Your role is manager");
        } else {
            Role manager = roleRepository.findByName("MANAGER")
                    .orElseThrow(() -> new ExpressionException("Role not found"));
            user.getRoles().add(manager);
            userRepository.save(user);
            return new MessageResponseDto(true, "Update role successfully !");
        }
    }


    private Role mapToDto(RoleDto roleDto) {
        Role role = new Role();
        role.setName(roleDto.getName());

        return role;
    }
}
