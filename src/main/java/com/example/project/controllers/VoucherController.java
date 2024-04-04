package com.example.project.controllers;

import com.example.project.dtos.request.CouponDto;
import com.example.project.dtos.request.VoucherDto;
import com.example.project.models.Voucher;
import com.example.project.services.VoucherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class VoucherController {
    private final VoucherService voucherService;

    @PostMapping(path = "voucher")
    public ResponseEntity<?> create(@RequestBody @Valid VoucherDto voucherDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation data", HttpStatus.BAD_REQUEST);
        }

        Voucher voucher = voucherService.save(voucherDto);

        return new ResponseEntity<>(voucher, HttpStatus.CREATED);
    }

    @PutMapping(path = "voucher/{id}")
    public ResponseEntity<?> update(
            @PathVariable UUID id,
            @RequestBody @Valid VoucherDto voucherDto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Validation data", HttpStatus.BAD_REQUEST);
        }
        if (!voucherService.isExist(id)) {
            return new ResponseEntity<>("Voucher not found", HttpStatus.NOT_FOUND);
        }

        Voucher voucher = voucherService.update(id, voucherDto);

        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }

    @GetMapping(path = "vouchers")
    public ResponseEntity<?> findAll() {
        List<Voucher> vouchers = voucherService.findAll();
        return new ResponseEntity<>(vouchers, HttpStatus.OK);
    }

    @PostMapping(path = "voucher/find")
    public ResponseEntity<?> findByCode(@RequestBody @Valid CouponDto coupon) {
        Voucher voucher = voucherService.findByCode(coupon.getCode());

        if (voucher == null) {
            return new ResponseEntity<>("Voucher not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(voucher, HttpStatus.OK);
    }

    @DeleteMapping(path = "voucher/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        if (!voucherService.isExist(id)) {
            return new ResponseEntity<>("Voucher not found", HttpStatus.NOT_FOUND);
        }

        voucherService.remove(id);

        return new ResponseEntity<>("Voucher deleted successfully", HttpStatus.OK);

    }

}