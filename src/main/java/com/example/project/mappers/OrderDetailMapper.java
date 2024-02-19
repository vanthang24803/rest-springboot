package com.example.project.mappers;


import com.example.project.dtos.request.OrderDetailDto;
import com.example.project.models.OrderDetail;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public record OrderDetailMapper(ModelMapper modelMapper) {
    public OrderDetail mapToDto(OrderDetailDto orderDetailDto) {
        return modelMapper.map(orderDetailDto, OrderDetail.class);
    }
}
