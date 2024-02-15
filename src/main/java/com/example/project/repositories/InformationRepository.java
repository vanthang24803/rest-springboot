package com.example.project.repositories;

import com.example.project.models.Information;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface InformationRepository extends JpaRepository<Information , UUID> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Information i WHERE i.product.id = ?1")
    void deleteByProductId(UUID productId);

    Information findAllByProductId(UUID productId);
}