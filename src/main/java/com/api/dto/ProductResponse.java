package com.api.dto;

import com.api.repository.entity.VendorEnum;
import lombok.Data;

@Data
public class ProductResponse {

    private String productName;
    private VendorEnum vendorEnum;
    private String productDescription;
    private int productQuantity;
    private Double productPrice;

}
