package com.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OrderDetailsRequest {

    @NotBlank(message = "Please provide the order number")
    private Long orderNumber;

    private int productId;
    private int quantityOrdered;
    private double priceEach;

}
