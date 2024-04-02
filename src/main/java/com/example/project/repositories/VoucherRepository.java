package com.example.project.repositories;

import com.example.project.models.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface VoucherRepository extends JpaRepository<Voucher, UUID> {
    Voucher findVoucherByCode(String code);
}
