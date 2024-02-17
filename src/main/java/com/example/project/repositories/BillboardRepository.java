package com.example.project.repositories;

import com.example.project.models.Billboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BillboardRepository extends JpaRepository<Billboard , UUID> {
}
