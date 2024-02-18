package com.example.project.repositories;

import com.example.project.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, UUID> {
    boolean existsByValue(String value);

    Optional<Token> findByValue(String value);

    Optional<Token> findByUserId(UUID id);
}
