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

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "productName")
    private String productName;

    @Enumerated(EnumType.STRING)
    @Column(name = "productVendor")
    private VendorEnum vendorEnum;

    @Column(name = "productDescription")
    private String productDescription;

    @Column(name = "productQuantity")
    private int productQuanity;

    @Column(name = "productPrice")
    private Double productPrice;

}
