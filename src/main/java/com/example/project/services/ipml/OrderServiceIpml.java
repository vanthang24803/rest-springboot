package com.example.project.services.ipml;

import com.example.project.dtos.request.OrderDto;
import com.example.project.dtos.request.UpdateOrderDto;
import com.example.project.dtos.request.UpdateOrderStatusDto;
import com.example.project.enums.Status;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Order;
import com.example.project.models.OrderDetail;
import com.example.project.repositories.OrderRepository;
import com.example.project.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceIpml implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order save(OrderDto orderDto) {
        Order order = mapToDto(orderDto);
        return orderRepository.save(order);
    }

    @Override
    public Order updateStatus(UUID id, UpdateOrderStatusDto updateOrderDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(updateOrderDto.getStatus());

        return orderRepository.save(order);
    }

    @Override
    public Order update(UUID id, UpdateOrderDto updateOrderDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        order.setCustomer(updateOrderDto.getCustomer());
        order.setEmail(updateOrderDto.getEmail());
        order.setNumberPhone(updateOrderDto.getNumberPhone());
        order.setAddress(updateOrderDto.getAddress());
        order.setPayment(updateOrderDto.getPayment());
        order.setShipping(updateOrderDto.isShipping());

        return orderRepository.save(order);
    }

    @Override
    public Status findStatus(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"))
                .getStatus();
    }

    @Override
    public boolean isExitById(UUID id) {
        return orderRepository.existsById(id);
    }

    @Override
    public Optional<Order> findOrder(UUID id) {
        return Optional.ofNullable(orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found")));
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public void remove(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    public Order mapToDto(OrderDto orderDto) {
        Order order = new Order();
        order.setEmail(orderDto.getEmail());
        order.setCustomer(orderDto.getCustomer());
        order.setNumberPhone(orderDto.getNumberPhone());
        order.setAddress(orderDto.getAddress());
        order.setPayment(orderDto.getPayment());
        order.setStatus(Status.PENDING);
        order.setShipping(orderDto.isShipping());
        order.setQuantity(orderDto.getQuantity());
        order.setTotalPrice(orderDto.getTotalPrice());

        List<OrderDetail> orderDetails = orderDto.getDetails().stream().map(detailDto -> {
            OrderDetail detail = new OrderDetail();
            detail.setProductId(detailDto.getProductId());
            detail.setOptionId(detailDto.getOptionId());
            detail.setName(detailDto.getName());
            detail.setThumbnail(detailDto.getThumbnail());
            detail.setOption(detailDto.getOption());
            detail.setPrice(detailDto.getPrice());
            detail.setSale(detailDto.getSale());
            detail.setQuantity(detailDto.getQuantity());
            detail.setOrder(order);
            return detail;
        }).collect(Collectors.toList());

        order.setDetails(orderDetails);

        return order;
    }

}
