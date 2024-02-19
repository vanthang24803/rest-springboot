package com.example.project.untils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QueryObject {
    private String name;
    private String category;
    private String sortBy;
    private String filter = "Lasted";
    private String status;
    private int limit = 20;
    private int page = 1;
}
