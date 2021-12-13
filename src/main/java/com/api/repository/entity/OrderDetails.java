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

    @Id
    @JoinColumn(name = "orderdetails_ordernumber", nullable = false)
    private Long orderNumber;

    @Column(name = "productId")
    @JoinColumn(name = "orderdetails_productid", nullable = false)
    private int productId;

    @Column(name = "quantityordered")
    private int quantityOrdered;

    @Column(name = "priceeach")
    private double priceEach;


}
