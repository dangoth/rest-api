package com.api.dto;

import com.api.repository.entity.VendorEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ProductRequest {

    @NotBlank(message = "Please provide a product name")
    @Size(min = 3, max = 20)
    private String productName;

    private VendorEnum vendorEnum;
    private String productDescription;
    private int productQuantity;
    private Double productPrice;

}
