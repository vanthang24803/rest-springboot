package com.example.project.services.ipml;

import com.example.project.dtos.request.OrderDto;
import com.example.project.dtos.request.UpdateOrderDto;
import com.example.project.dtos.request.UpdateOrderStatusDto;
import com.example.project.enums.Status;
import com.example.project.exceptions.ResourceNotFoundException;
import com.example.project.models.Order;
import com.example.project.models.OrderDetail;
import com.example.project.repositories.OptionRepository;
import com.example.project.repositories.OrderRepository;
import com.example.project.models.Option;
import com.example.project.services.OrderService;
import com.example.project.services.SendMailService;
import com.example.project.untils.EmailTemplateUntils;
import com.example.project.untils.ExcelExportUtils;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceIpml implements OrderService {

    private final OrderRepository orderRepository;
    private final OptionRepository optionRepository;
    private final SendMailService mailService;

    @Override
    public Order save(OrderDto orderDto) {
        Order order = mapToDto(orderDto);
        orderRepository.save(order);
        for (OrderDetail detail : order.getDetails()) {
            Option option = optionRepository.
                    findById(UUID.fromString(detail.getOptionId()))
                    .orElseThrow(() -> new RuntimeException("Option not found"));

            int newQuantity = option.getQuantity() - detail.getQuantity();
            option.setQuantity(newQuantity);

            if (newQuantity == 0) {
                option.setStatus(false);
            }
            optionRepository.save(option);
        }

        String toEmail = order.getEmail();
        String subject = "Order confirmation";
        String body =  EmailTemplateUntils.createOrderConfirmationEmailBody(order);

        mailService.sendMail(toEmail, subject, body);

        return order;
    }

    @Override
    public Order updateStatus(UUID id, UpdateOrderStatusDto updateOrderDto) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        order.setStatus(updateOrderDto.getStatus());

        orderRepository.save(order);

        String toEmail = order.getEmail();
        String subject = "Order update" + " " + order.getStatus();
        String message =  EmailTemplateUntils.createOrderConfirmationEmailBody(order);

        mailService.sendMail(toEmail, subject, message);

        return order;
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

        String toEmail = order.getEmail();
        String subject = "Order update information";
        String message =  EmailTemplateUntils.createOrderConfirmationEmailBody(order);

        mailService.sendMail(toEmail, subject, message);

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
    public void exportExcel(HttpServletResponse response) {
        List<Order> orders = orderRepository.findAll();

        ExcelExportUtils excelExportUtils = new ExcelExportUtils();
        excelExportUtils.exportOrdersToExcel(orders);
        excelExportUtils.exportOrderDetailsToExcel(orders);

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=orders.xlsx");

        try (OutputStream outputStream = response.getOutputStream()) {
            excelExportUtils.getWorkbook().write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
