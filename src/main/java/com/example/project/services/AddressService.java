package com.example.project.services;

import com.example.project.dtos.request.AddressDto;
import com.example.project.models.Address;

import java.util.List;
import java.util.UUID;

public interface AddressService {
    Address save(String email , AddressDto addressDto);

    Address update( UUID id, AddressDto addressDto);

    boolean isExist(UUID id);

    List<Address> findByProduct(String email);

    void remove(UUID id);
}
