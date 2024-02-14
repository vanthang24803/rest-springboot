package com.example.project.repositories;

import com.example.project.models.Information;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InformationRepository extends JpaRepository<Information , UUID> {
}