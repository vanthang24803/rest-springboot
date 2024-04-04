package com.example.project.controllers;

import com.example.project.dtos.request.AddressDto;
import com.example.project.models.Address;
import com.example.project.services.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/profile")
public class AddressController {
    private final AddressService addressService;

    @PostMapping(path = "address")
    public ResponseEntity<?> create(
            Principal principal, @RequestBody @Valid AddressDto addressDto) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        Address address = addressService.save(email, addressDto);

        return new ResponseEntity<>(address, HttpStatus.CREATED);
    }

    @GetMapping(path = "address")
    public ResponseEntity<?> getAllAddressOfUser(Principal principal) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }
        String email = principal.getName();

        List<Address> addresses = addressService.findByProduct(email);

        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }

    @PutMapping(path = "address/{id}")
    public ResponseEntity<?> updateAddress(
            Principal principal, @PathVariable UUID id, @RequestBody @Valid AddressDto addressDto
    ) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        if (!addressService.isExist(id)) {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }

        Address address = addressService.update(id, addressDto);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

    @DeleteMapping(path = "address/{id}")
    public ResponseEntity<?> deleteAddress(
            Principal principal, @PathVariable UUID id
    ) {
        if (principal == null) {
            return new ResponseEntity<>("Token not found", HttpStatus.NOT_FOUND);
        }

        if (!addressService.isExist(id)) {
            return new ResponseEntity<>("Address not found", HttpStatus.NOT_FOUND);
        }
        addressService.remove(id);

        return new ResponseEntity<>("Address deleted successfully", HttpStatus.OK);
    }
}
