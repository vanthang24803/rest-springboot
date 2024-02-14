package com.example.project.services;

import com.example.project.dtos.request.OptionDto;
import com.example.project.models.Option;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OptionService {
    Option save(UUID productId, OptionDto optionDto);

    List<Option> findProductOptions(UUID productId);

    Optional<Option> findOptionById(UUID productId, UUID optionId);

    boolean isOptionExists(UUID id);

    boolean isProductExits(UUID id);

    Option update(UUID productId, UUID optionId, OptionDto optionDto);

    void delete(UUID productId, UUID optionId);
}
