package com.example.project.controllers;

import com.example.project.dtos.request.VoucherDto;
import com.example.project.models.Voucher;
import com.example.project.services.VoucherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class VoucherController {
    private final VoucherService voucherService;

    @PostMapping(path = "voucher")
    public ResponseEntity<?> create(@RequestBody VoucherDto voucherDto) {
        Voucher voucher = voucherService.save(voucherDto);

        return new ResponseEntity<>(voucher, HttpStatus.CREATED);
    }

    @PutMapping(path = "voucher/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody VoucherDto voucherDto
    ) {
        if (!voucherService.isExist(id)) {
            return new ResponseEntity<>("Voucher not found", HttpStatus.NOT_FOUND);
        }

        Voucher voucher = voucherService.update(id, voucherDto);

        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }
}