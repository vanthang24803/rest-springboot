package com.example.project.controllers;

import com.example.project.dtos.request.OptionDto;
import com.example.project.models.Option;
import com.example.project.services.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class OptionController {
    private final OptionService optionService;

    @PostMapping(path = "{productId}/option")
    public ResponseEntity<?> createOption(
            @PathVariable UUID productId, @RequestBody OptionDto optionDto
    ) {
        if (!optionService.isProductExits(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        Option option = optionService.save(productId, optionDto);

        return new ResponseEntity<>(option, HttpStatus.CREATED);
    }

    @GetMapping(path = "{productId}/options")
    public ResponseEntity<?> findOptionOfProduct(
            @PathVariable UUID productId
    ) {
        if (!optionService.isProductExits(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        List<Option> options = optionService.findProductOptions(productId);
        return new ResponseEntity<>(options, HttpStatus.OK);
    }

    @GetMapping(path = "{productId}/option/{optionId}")
    public ResponseEntity<?> findOptionById(
            @PathVariable UUID productId, @PathVariable UUID optionId
    ) {
        if (!optionService.isProductExits(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        if (!optionService.isOptionExists(optionId)) {
            return new ResponseEntity<>("Option not found", HttpStatus.NOT_FOUND);
        }

        Optional<Option> option = optionService.findOptionById(productId, optionId);
        return new ResponseEntity<>(option, HttpStatus.OK);
    }

    @PutMapping(path = "{productId}/option/{optionId}")
    public ResponseEntity<?> updateOption(
            @PathVariable UUID productId, @PathVariable UUID optionId,
            @RequestBody OptionDto optionDto
    ) {
        if (!optionService.isProductExits(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        if (!optionService.isOptionExists(optionId)) {
            return new ResponseEntity<>("Option not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionService.update(productId, optionId, optionDto),
                HttpStatus.OK);
    }

    @DeleteMapping(path = "{productId}/option/{optionId}")
    public ResponseEntity<String> deleteOption(
            @PathVariable UUID productId, @PathVariable UUID optionId
    ) {
        if (!optionService.isProductExits(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        if (!optionService.isOptionExists(optionId)) {
            return new ResponseEntity<>("Option not found", HttpStatus.NOT_FOUND);
        }
        optionService.delete(productId, optionId);

        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
