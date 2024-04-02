package com.example.project.services.ipml;

import com.example.project.dtos.request.VoucherDto;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.mappers.VoucherMapper;
import com.example.project.models.Voucher;
import com.example.project.repositories.VoucherRepository;
import com.example.project.services.VoucherService;
import com.example.project.untils.CodeGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VoucherServiceIpml implements VoucherService {

    private final VoucherRepository voucherRepository;
    private final VoucherMapper voucherMapper;

    @Override
    public Voucher save(VoucherDto voucherDto) {
        Voucher voucher = voucherMapper.mapToDto(voucherDto);

        voucher.setCode(CodeGeneratorUtil.generateCode(5));

        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher update(UUID id, VoucherDto voucherDto) {
        Voucher existingVoucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not found"));

        existingVoucher.setName(voucherDto.getName());
        existingVoucher.setTitle(voucherDto.getTitle());
        existingVoucher.setQuantity(voucherDto.getQuantity());
        existingVoucher.setCoupon(voucherDto.getCoupon());
        existingVoucher.setQuantity(voucherDto.getQuantity());
        existingVoucher.setDay(voucherDto.getDay());
        existingVoucher.setDiscount(voucherDto.getDiscount());
        existingVoucher.setShelfLife(voucherDto.getShelfLife());

        if (existingVoucher.getQuantity() < 1) {
            existingVoucher.setExpire(true);
        }

        return voucherRepository.save(existingVoucher);
    }

    @Override
    public void remove(UUID id) {
        Voucher existingVoucher = voucherRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Voucher not found"));

        voucherRepository.delete(existingVoucher);
    }

    @Override
    public boolean isExist(UUID id) {
        return voucherRepository.existsById(id);
    }

    @Override
    public Voucher findByCode(String code) {
        return voucherRepository.findVoucherByCode(code);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }
}
