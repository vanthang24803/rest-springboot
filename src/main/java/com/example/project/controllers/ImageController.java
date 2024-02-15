package com.example.project.controllers;

import com.example.project.models.Image;
import com.example.project.services.ImageService;
import com.example.project.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ImageController {
    private final ImageService imageService;
    private final ProductService productService;

    @PostMapping(path = "{productId}/image")
    public ResponseEntity<?> create(
            @PathVariable UUID productId,
            @RequestParam("files") List<MultipartFile> multipartFiles
    ) {
        if (!productService.isExists(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        List<Image> images = imageService.save(productId, multipartFiles);
        return ResponseEntity.ok(images);
    }

    @GetMapping(path = "{productId}/images")
    public ResponseEntity<?> getImagesOfProduct(
            @PathVariable UUID productId
    ) {
        if (!productService.isExists(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        List<Image> images = imageService.findAllByProductId(productId);
        return ResponseEntity.ok(images);
    }

    @DeleteMapping(path = "{productId}/image/{imageId}")
    public ResponseEntity<?> deleteImage(
            @PathVariable UUID productId, @PathVariable UUID imageId
    ) {
        if (!productService.isExists(productId)) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }
        if (!imageService.isExists(imageId)) {
            return new ResponseEntity<>("Image not found", HttpStatus.NOT_FOUND);
        }

        imageService.remove(imageId);

        return ResponseEntity.ok("Image deleted successfully");
    }
}
