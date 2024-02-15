package com.example.project.controllers;

import com.example.project.dtos.request.InformationDto;
import com.example.project.models.Information;
import com.example.project.services.InformationService;
import com.example.project.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class InformationController {
    private final ProductService productService;
    private final InformationService informationService;

    @PostMapping(path = "{productId}/information")
    public ResponseEntity<?> create(
            @PathVariable UUID productId, @RequestBody InformationDto informationDto
    ) {
        if (!productService.isExists(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        Information information = informationService.save(productId, informationDto);
        return new ResponseEntity<>(information, HttpStatus.CREATED);
    }

    @GetMapping(path = "{productId}/information")
    public ResponseEntity<?> findInformationOfProduct(
            @PathVariable UUID productId
    ) {
        if (!productService.isExists(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(informationService.findByProduct(productId), HttpStatus.OK);
    }

    @PutMapping(path = "{productId}/information")
    public ResponseEntity<?> update(
            @PathVariable UUID productId, @RequestBody InformationDto informationDto
    ) {
        if (!productService.isExists(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(informationService.update(productId, informationDto),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "{productId}/information")
    public ResponseEntity<?> remove(
            @PathVariable UUID productId
    ) {
        if (!productService.isExists(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        informationService.remove(productId);

        return new ResponseEntity<>("Information deleted successfully", HttpStatus.OK);
    }
}
