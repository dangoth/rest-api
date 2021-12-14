package com.api.dtomapper;

import com.api.dto.CustomersResponse;
import com.api.repository.CustomersRepository;
import com.api.repository.entity.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    @Autowired
    private CustomersRepository customersRepository;

    public CustomersResponse convertToCustomerDTO(Customers customer) {
        CustomersResponse response = new CustomersResponse();
        response.setCustomerName(customer.getCustomerName());
        response.setContactFirstName(customer.getContactFirstName());
        response.setContactLastName(customer.getContactLastName());
        response.setPhoneNumber(customer.getPhoneNumber());
        response.setAddress(customer.getAddress());
        response.setCity(customer.getCity());
        response.setPostalCode(customer.getPostalCode());
        response.setCountry(customer.getCountry());
        return response;
    }

}
