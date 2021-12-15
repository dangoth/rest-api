package com.api.dto;

import com.api.repository.entity.Customers;
import com.api.repository.entity.StatusEnum;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class OrdersResponse {

    private LocalDate orderDate;
    private StatusEnum statusEnum;
    private Customers customer;

}
