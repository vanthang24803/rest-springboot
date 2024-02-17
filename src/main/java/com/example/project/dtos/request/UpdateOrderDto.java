package com.example.project.dtos.request;

import com.example.project.enums.Payment;
import com.example.project.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateOrderDto {
    private String email;

    private String customer;

    private String numberPhone;

    private String address;

    @Enumerated(EnumType.STRING)
    private Payment payment;

    private boolean shipping;
}
