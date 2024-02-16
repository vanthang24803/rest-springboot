package com.example.project.repositories;

import com.example.project.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address , UUID> {
}
