package com.example.project.repositories;

import com.example.project.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderDetailRepository extends JpaRepository<OrderDetail , UUID> {

}
