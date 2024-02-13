package com.example.project.repositories;

import com.example.project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product , UUID> {

}
