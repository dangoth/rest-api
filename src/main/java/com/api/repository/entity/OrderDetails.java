package com.api.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orderDetails")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderDetails {

    @Column(name = "orderNumber")
    @Id
    @JoinColumn(name = "orderdetails_orderNumber", nullable = false)
    private Long orderNumber;

    @Column(name = "productId")
    @JoinColumn(name = "orderdetails_productId", nullable = false)
    private int productId;

    @Column(name = "quantityOrdered")
    private int quantityOrdered;

    @Column(name = "priceEach")
    private double priceEach;


}
