package com.api.dto;

import com.api.repository.entity.StatusEnum;
import lombok.Data;

import java.util.Date;

@Data
public class OrdersResponse {

    private Long orderId;
    private Date orderDate;
    private StatusEnum statusEnum;
    private int customerNumber;
}
