package com.example.project.services;

import com.example.project.models.Billboard;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface BillboardService {
    Billboard save(String url, MultipartFile file);

    Billboard update(UUID id , String url, MultipartFile file);

    List<Billboard> findAll();

    boolean isExits(UUID id);

    void delete(UUID id);

}
