package com.example.project.mappers;

import com.example.project.dtos.request.AddressDto;
import com.example.project.models.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record AddressMapper(ModelMapper modelMapper) {
    public Address mapToDto(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }
}
