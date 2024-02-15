package com.example.project.services;

import com.example.project.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    List<Image> save(UUID productId,List<MultipartFile> multipartFiles);

    List<Image> findAllByProductId(UUID productId);

    boolean isExists(UUID id);

    void remove(UUID imageId);
}
