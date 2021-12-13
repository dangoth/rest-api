package com.api.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "productname")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "productvendor")
    private VendorEnum vendorEnum;

    @Column(name = "productdescription")
    private String productDescription;

    @Column(name = "productquantity")
    private int productQuanity;

    @Column(name = "productprice")
    private Double productPrice;

}
