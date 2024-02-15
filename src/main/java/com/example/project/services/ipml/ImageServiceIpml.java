package com.example.project.services.ipml;

import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Image;
import com.example.project.models.Product;
import com.example.project.repositories.ImageRepository;
import com.example.project.repositories.ProductRepository;
import com.example.project.services.ImageService;
import com.example.project.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ImageServiceIpml implements ImageService {
    private final UploadService uploadService;
    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;


    @Override
    public List<Image> save(UUID productId, List<MultipartFile> multipartFiles) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        List<Image> images = new ArrayList<>();

        for (MultipartFile file : multipartFiles) {
            try {
                String url = uploadService.uploadFile(file);
                Image image = new Image();
                image.setUrl(url);
                image.setProduct(product);
                images.add(imageRepository.save(image));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return images;
    }

    @Override
    public List<Image> findAllByProductId(UUID productId) {
        return imageRepository.findAllByProductId(productId);
    }

    @Override
    public boolean isExists(UUID id) {
        return imageRepository.existsById(id);
    }

    @Override
    public void remove( UUID imageId) {
        imageRepository.deleteById(imageId);
    }

}
