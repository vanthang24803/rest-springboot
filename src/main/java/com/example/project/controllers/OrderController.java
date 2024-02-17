package com.example.project.controllers;

import com.example.project.dtos.request.OrderDto;
import com.example.project.dtos.request.UpdateOrderDto;
import com.example.project.dtos.request.UpdateOrderStatusDto;
import com.example.project.enums.Status;
import com.example.project.models.Order;
import com.example.project.services.OrderService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1")
public class OrderController {
    private final OrderService orderService;

    @PostMapping(path = "order")
    public ResponseEntity<?> create(@RequestBody OrderDto orderDto) {
        Order order = orderService.save(orderDto);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping(path = "orders")
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderService.findAll();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(path = "order/export")
    public ResponseEntity<?> export(HttpServletResponse response) {
        try {
            orderService.exportExcel(response);
            return ResponseEntity.ok("Orders exported successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while exporting the orders: " + e.getMessage());
        }
    }


    @GetMapping(path = "order/{id}")
    public ResponseEntity<?> find(@PathVariable UUID id) {
        if (!orderService.isExitById(id)) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderService.findOrder(id), HttpStatus.OK);
    }

    @PutMapping(path = "order/{id}")
    public ResponseEntity<?> updateStatus(
            @PathVariable UUID id,
            @RequestBody UpdateOrderDto updateOrderDto
    ) {
        if (!orderService.isExitById(id)) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
        Order order = orderService.update(id, updateOrderDto);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @PutMapping(path = "order/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable UUID id,
            @RequestBody UpdateOrderStatusDto updateOrderDto
    ) {
        if (!orderService.isExitById(id)) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }

        Order order = orderService.updateStatus(id, updateOrderDto);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping(path = "order/{id}")
    public ResponseEntity<String> remove(@PathVariable UUID id) {
        if (!orderService.isExitById(id)) {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
        Status status = orderService.findStatus(id);

        if (status != Status.PENDING && status != Status.SUCCESS) {
            return new ResponseEntity<>("Order status must be PENDING or SUCCESS to perform this " +
                    "action", HttpStatus.BAD_REQUEST);
        }

        orderService.remove(id);
        return ResponseEntity.ok("Order deleted successfully");
    }

}
