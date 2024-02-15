package com.example.project.repositories;

import com.example.project.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image , UUID> {
    List<Image> findAllByProductId(UUID productId);
}
