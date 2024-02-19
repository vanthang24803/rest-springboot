package com.example.project.mappers;

import com.example.project.dtos.request.OrderDto;
import com.example.project.models.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record OrderMapper(ModelMapper modelMapper) {
    public Order mapToDto(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }
}
