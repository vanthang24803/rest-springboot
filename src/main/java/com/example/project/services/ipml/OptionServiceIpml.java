package com.example.project.services.ipml;

import com.example.project.dtos.request.OptionDto;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.mappers.OptionMapper;
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
    private final OptionMapper optionMapper;

    @Override
    public Option save(UUID productId, OptionDto optionDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Option option = optionMapper.mapToDto(optionDto);
        option.setProduct(product);
        return optionRepository.save(option);
    }

    @Override
    public List<Option> findProductOptions(UUID productId) {
        List<Option> options = optionRepository.findAllByProductId(productId);
        return new ArrayList<>(options);
    }

    @Override
    public Optional<Option> findOptionById(UUID productId, UUID optionId) {
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
        return optionRepository.findById(optionId).map(
                exitingOption -> {
                    Optional.ofNullable(optionDto.getName()).ifPresent(exitingOption::setName);
                    Optional.of(optionDto.getQuantity()).ifPresent(exitingOption::setQuantity);
                    Optional.of(optionDto.getPrice()).ifPresent(exitingOption::setPrice);
                    Optional.of(optionDto.getSale()).ifPresent(exitingOption::setSale);
                    exitingOption.setStatus(optionDto.getQuantity() > 0);
                    return optionRepository.save(exitingOption);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Option not found"));
    }

    @Override
    public void delete(UUID productId, UUID optionId) {
        optionRepository.deleteById(optionId);
    }

}
