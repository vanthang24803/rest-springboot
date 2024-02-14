package com.example.project.services.ipml;

import com.example.project.dtos.request.OptionDto;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Option;
import com.example.project.models.Product;
import com.example.project.repositories.OptionRepository;
import com.example.project.repositories.ProductRepository;
import com.example.project.services.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OptionServiceIpml implements OptionService {
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    @Override
    public Option save(UUID productId, OptionDto optionDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Option option = mapToDto(optionDto);
        option.setProduct(product);
        return optionRepository.save(option);
    }

    @Override
    public List<Option> findProductOptions(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        List<Option> options = optionRepository.findAllByProductId(productId);
        return new ArrayList<>(options);
    }

    @Override
    public Optional<Option> findOptionById(UUID productId, UUID optionId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found"));

        return optionRepository.findById(optionId);
    }


    @Override
    public boolean isOptionExists(UUID id) {
        return optionRepository.existsById(id);
    }

    @Override
    public boolean isProductExits(UUID id) {
        return productRepository.existsById(id);
    }

    @Override
    public Option update(UUID productId, UUID optionId, OptionDto optionDto) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));


        return optionRepository.findById(optionId).map(
                exitingOption -> {
                    Optional.ofNullable(optionDto.getName()).ifPresent(exitingOption::setName);
                    Optional.of(optionDto.getQuantity()).ifPresent(exitingOption::setQuantity);
                    Optional.of(optionDto.getPrice()).ifPresent(exitingOption::setPrice);
                    Optional.of(optionDto.getSale()).ifPresent(exitingOption::setSale);
                    return optionRepository.save(exitingOption);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Option not found"));
    }

    @Override
    public void delete(UUID productId, UUID optionId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Option option = optionRepository.findById(optionId)
                .orElseThrow(() -> new ResourceNotFoundException("Option not found"));

        optionRepository.deleteById(optionId);
    }

    private Option mapToDto(OptionDto optionDto) {
        return Option.builder()
                .name(optionDto.getName())
                .sale(optionDto.getSale())
                .quantity(optionDto.getQuantity())
                .price(optionDto.getPrice())
                .status(true)
                .build();
    }

}
