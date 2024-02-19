package com.example.project.mappers;


import com.example.project.dtos.request.InformationDto;
import com.example.project.models.Information;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record InformationMapper(ModelMapper modelMapper) {
    public Information mapToDto(InformationDto informationDto) {
        return modelMapper.map(informationDto, Information.class);
    }
}

