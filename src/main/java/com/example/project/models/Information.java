package com.example.project.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String author;

    private String translator;

    private String category;

    private String format;

    private String pages;

    private  String gift;

    private String price;

    @OneToOne(mappedBy = "information")
    private Product product;
}