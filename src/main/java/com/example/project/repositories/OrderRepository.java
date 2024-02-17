package com.example.project.repositories;

import com.example.project.enums.Status;
import com.example.project.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
