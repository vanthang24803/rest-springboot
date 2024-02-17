package com.example.project.controllers;

import com.example.project.models.Billboard;
import com.example.project.services.BillboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BillboardController {
    private final BillboardService billboardService;

    @PostMapping(path = "billboard")
    public ResponseEntity<?> create(
            @RequestParam("url") String url,
            @RequestParam("file") MultipartFile file
    ) {
        Billboard billboard = billboardService.save(url, file);
        return new ResponseEntity<>(billboard, HttpStatus.CREATED);
    }

    @GetMapping(path = "billboards")
    public ResponseEntity<List<Billboard>> getAll() {
        List<Billboard> billboards = billboardService.findAll();
        return ResponseEntity.ok(billboards);
    }

    @PutMapping(path = "billboard/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestParam("url") String url,
            @RequestParam("file") MultipartFile file
    ) {
        if (!billboardService.isExits(id)) {
            return new ResponseEntity<>("Billboard not found", HttpStatus.NOT_FOUND);
        }

        Billboard billboard = billboardService.update(id, url, file);
        return new ResponseEntity<>(billboard, HttpStatus.OK);
    }

    @DeleteMapping(path = "billboard/{id}")
    public ResponseEntity<String> remove(@PathVariable UUID id) {
        if (!billboardService.isExits(id)) {
            return new ResponseEntity<>("Billboard not found", HttpStatus.NOT_FOUND);
        }

        billboardService.delete(id);
        return ResponseEntity.ok("Billboard deleted successfully!");
    }
}
