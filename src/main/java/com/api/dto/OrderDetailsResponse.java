package com.api.dto;

import lombok.Data;

@Data
public class OrderDetailsResponse {

    private Long orderNumber;
    private int productId;
    private int quantityOrdered;
    private double priceEach;

}
