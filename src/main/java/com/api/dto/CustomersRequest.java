package com.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CustomersRequest {

    @NotBlank(message = "Please provide a customer name")
    @Size(min=3, max=20)
    private String customerName;

    private String contactFirstName;
    private String contactLastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String postalCode;
    private String country;

}
