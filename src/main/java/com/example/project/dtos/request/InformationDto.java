package com.example.project.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InformationDto {
    private String author;

    private String translator;

    private String category;

    private String format;

    private String numberOfPage;

    private String isbn;

    private String publisher;

    private String company;

    private String gift;

    private String price;

    private String released;

    private String introduce;
}
