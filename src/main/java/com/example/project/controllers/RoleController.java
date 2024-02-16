package com.example.project.controllers;

import com.example.project.dtos.response.MessageResponseDto;
import com.example.project.dtos.response.RoleDto;
import com.example.project.models.Role;
import com.example.project.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("role")
    public ResponseEntity<?> create(
            Principal principal, @RequestBody RoleDto roleDto
    ) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        Role role = roleService.save(roleDto);

        return new ResponseEntity<>(role, HttpStatus.CREATED);
    }

    @GetMapping("roles")
    public ResponseEntity<?> getAll(
            Principal principal
    ) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        List<Role> roles = roleService.findAll();

        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @DeleteMapping(path = "role/{id}")
    public ResponseEntity<String> remove(Principal principal, @PathVariable UUID id) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        if (!roleService.isExist(id)) {
            return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
        }
        roleService.delete(id);

        return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
    }

    @DeleteMapping(path = "role/profile/{id}")
    public ResponseEntity<String> removeRoleOfProduct(Principal principal, @PathVariable UUID id) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        if (!roleService.isExist(id)) {
            return new ResponseEntity<>("Role not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        roleService.deleteRoleOfProduct(id, email);

        return new ResponseEntity<>("Role deleted successfully", HttpStatus.OK);
    }


    @PostMapping(path = "role/manager")
    public ResponseEntity<?> upgradeManager(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        MessageResponseDto message = roleService.upgradeToManager(email);

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

    @PostMapping(path = "role/admin")
    public ResponseEntity<?> upgradeAdmin(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        MessageResponseDto message = roleService.upgradeToAdmin(email);

        return new ResponseEntity<>(message, HttpStatus.OK);

    }

}
