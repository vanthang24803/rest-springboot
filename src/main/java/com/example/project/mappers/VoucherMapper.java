package com.example.project.mappers;


import com.example.project.dtos.request.VoucherDto;
import com.example.project.models.Voucher;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record VoucherMapper(ModelMapper modelMapper) {
    public Voucher  mapToDto(VoucherDto voucherDto) {
        return modelMapper.map(voucherDto, Voucher.class);
    }
}
