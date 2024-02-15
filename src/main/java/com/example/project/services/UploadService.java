package com.example.project.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String uploadFile(MultipartFile multipartFile) throws IOException;
}
