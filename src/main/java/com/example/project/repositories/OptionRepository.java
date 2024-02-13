package com.example.project.repositories;

import com.example.project.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OptionRepository extends JpaRepository<Option , UUID> {
}
