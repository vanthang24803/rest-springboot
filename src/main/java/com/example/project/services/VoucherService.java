package com.example.project.services;

import com.example.project.dtos.request.VoucherDto;
import com.example.project.models.Voucher;

import java.util.List;
import java.util.UUID;

public interface VoucherService {
    Voucher save(VoucherDto voucherDto);

    Voucher update(UUID id, VoucherDto voucherDto);

    void remove(UUID id);

    boolean isExist(UUID id);

    Voucher findByCode(String code);

    List<Voucher> findAll();
}
