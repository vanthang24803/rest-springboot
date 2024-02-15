package com.example.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private String numberOfPage;

    private String isbn;

    private String publisher;

    private String company;
    @Column(columnDefinition = "TEXT")
    private String gift;

    private String price;

    private String released;
    @Column(columnDefinition = "TEXT")
    private String introduce;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}