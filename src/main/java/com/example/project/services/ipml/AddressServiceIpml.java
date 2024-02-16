package com.example.project.services.ipml;

import com.example.project.dtos.request.AddressDto;
import com.example.project.models.Address;
import com.example.project.models.UserEntity;
import com.example.project.repositories.AddressRepository;
import com.example.project.repositories.UserRepository;
import com.example.project.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AddressServiceIpml implements AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    @Override
    public Address save(String email, AddressDto addressDto) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Address address = mapToDto(addressDto);

        address.setUser(user);

        return addressRepository.save(address);
    }

    @Override
    public Address update(UUID id, AddressDto addressDto) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Address not found"));

        address.setName(addressDto.getName());

        return addressRepository.save(address);
    }

    @Override
    public boolean isExist(UUID id) {
        return addressRepository.existsById(id);
    }

    @Override
    public List<Address> findByProduct(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return addressRepository.findAllByUserId(user.getId());
    }

    @Override
    public void remove(UUID id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Address not found"));
        addressRepository.delete(address);
    }

    private Address mapToDto(AddressDto addressDto) {
        Address address = new Address();
        address.setName(addressDto.getName());
        return address;
    }
}
