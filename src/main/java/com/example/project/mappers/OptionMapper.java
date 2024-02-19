package com.example.project.mappers;

import com.example.project.dtos.request.OptionDto;
import com.example.project.models.Option;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record OptionMapper(ModelMapper modelMapper) {
    public Option mapToDto(OptionDto optionDto) {
        Option option = modelMapper.map(optionDto, Option.class);
        option.setStatus(optionDto.getQuantity() > 0);
        return option;
    }
}
