package com.example.project.services.ipml;

import com.example.project.dtos.request.InformationDto;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.mappers.InformationMapper;
import com.example.project.models.Information;
import com.example.project.models.Product;
import com.example.project.repositories.InformationRepository;
import com.example.project.repositories.ProductRepository;
import com.example.project.services.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InformationServiceIpml implements InformationService {
    private final ProductRepository productRepository;
    private final InformationRepository informationRepository;
    private final InformationMapper informationMapper;

    @Override
    public Information save(UUID productId, InformationDto informationDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        Information information = informationMapper.mapToDto(informationDto);

        information.setProduct(product);

        return informationRepository.save(information);
    }

    @Override
    public Information update(UUID id, InformationDto informationDto) {
        return informationRepository.findById(id).map(
                exitingInformation -> {
                    Optional.ofNullable(informationDto.getAuthor()).ifPresent(exitingInformation::setAuthor);
                    Optional.ofNullable(informationDto.getTranslator()).ifPresent(exitingInformation::setTranslator);
                    Optional.ofNullable(informationDto.getCategory()).ifPresent(exitingInformation::setCategory);
                    Optional.ofNullable(informationDto.getFormat()).ifPresent(exitingInformation::setFormat);
                    Optional.ofNullable(informationDto.getNumberOfPage()).ifPresent(exitingInformation::setNumberOfPage);
                    Optional.ofNullable(informationDto.getIsbn()).ifPresent(exitingInformation::setIsbn);
                    Optional.ofNullable(informationDto.getPublisher()).ifPresent(exitingInformation::setPublisher);
                    Optional.ofNullable(informationDto.getCompany()).ifPresent(exitingInformation::setCompany);
                    Optional.ofNullable(informationDto.getGift()).ifPresent(exitingInformation::setGift);
                    Optional.ofNullable(informationDto.getPrice()).ifPresent(exitingInformation::setPrice);
                    Optional.ofNullable(informationDto.getReleased()).ifPresent(exitingInformation::setReleased);
                    Optional.ofNullable(informationDto.getIntroduce()).ifPresent(exitingInformation::setIntroduce);

                    return informationRepository.save(exitingInformation);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("Option not found"));
    }

    @Override
    public Information findByProduct(UUID productId) {
        return informationRepository.findAllByProductId(productId);
    }

    @Override
    public void remove(UUID productId) {
        informationRepository.deleteByProductId(productId);
    }

}
