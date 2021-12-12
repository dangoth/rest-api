package com.api.dto;

import com.api.repository.entity.StatusEnum;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class OrdersRequest {

    @NotBlank(message = "please provide an order id")
    private Long orderId;
    private Date orderDate;
    private StatusEnum statusEnum;
    private int customerNumber;
}
