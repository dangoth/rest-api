package com.api.dto;

import lombok.Data;

@Data
public class CustomersResponse {

    private String customerName;
    private String contactFirstName;
    private String contactLastName;
    private String phoneNumber;
    private String address;
    private String city;
    private String postalCode;
    private String country;

}
