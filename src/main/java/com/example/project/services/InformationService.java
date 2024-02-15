package com.example.project.services;


import com.example.project.dtos.request.InformationDto;
import com.example.project.models.Information;

import java.util.UUID;

public interface InformationService {
    Information save(UUID productId, InformationDto informationDto);

    Information update(UUID id, InformationDto informationDto);

    Information findByProduct(UUID productId);

    void remove(UUID productId);
}
