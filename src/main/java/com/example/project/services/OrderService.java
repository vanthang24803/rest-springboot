package com.example.project.services;

import com.example.project.dtos.request.OrderDto;
import com.example.project.dtos.request.UpdateOrderDto;
import com.example.project.dtos.request.UpdateOrderStatusDto;
import com.example.project.enums.Status;
import com.example.project.models.Order;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    Order save(OrderDto orderDto);

    Order updateStatus(UUID id, UpdateOrderStatusDto updateOrderDto);

    Order update(UUID id, UpdateOrderDto updateOrderDto);

    Status findStatus(UUID id);

    boolean isExitById(UUID id);

    Optional<Order> findOrder(UUID id);

    List<Order> findAll();

    void exportExcel(HttpServletResponse response);

    void remove(UUID id);
}
