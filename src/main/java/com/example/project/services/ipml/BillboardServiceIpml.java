package com.example.project.services.ipml;

import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Billboard;
import com.example.project.repositories.BillboardRepository;
import com.example.project.services.BillboardService;
import com.example.project.services.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BillboardServiceIpml implements BillboardService {
    private final BillboardRepository billboardRepository;
    private final UploadService uploadService;

    @Override
    public Billboard save(String url, MultipartFile file) {
        Billboard billboard = new Billboard();
        try {
            String image = uploadService.uploadFile(file);

            billboard.setImage(image);
            billboard.setUrl(url);

            billboardRepository.save(billboard);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return billboard;
    }

    @Override
    public Billboard update(UUID id, String url, MultipartFile file) {
        Billboard billboard = billboardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Billboard not found"));
        try {
            String image = uploadService.uploadFile(file);

            billboard.setImage(image);
            billboard.setUrl(url);

            billboardRepository.save(billboard);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return billboard;
    }

    @Override
    public List<Billboard> findAll() {
        return billboardRepository.findAll();
    }

    @Override
    public boolean isExits(UUID id) {
        return billboardRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        Billboard billboard = billboardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Billboard not found"));

        billboardRepository.delete(billboard);
    }
}
