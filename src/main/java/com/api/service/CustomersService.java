package com.api.service;

import com.api.repository.CustomersRepository;
import com.api.repository.entity.Customers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomersService {

    private CustomersRepository customersRepository;

    public Customers addCustomer(String customerName, String contactFirstName,
                                 String contactLastName, String phoneNumber,
                                 String address, String city,
                                 String postalCode, String country) {
        Customers customer = Customers.builder()
                .customerName(customerName)
                .contactFirstName(contactFirstName)
                .contactLastName(contactLastName)
                .phoneNumber(phoneNumber)
                .address(address)
                .city(city)
                .postalCode(postalCode)
                .country(country)
                .build();

        return customersRepository.save(customer);
    }

    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }

    public boolean updateCustomer(long id, Customers updatedCustomer) {
        Optional<Customers> currentCustomer = customersRepository.findById(id);
        if (currentCustomer.isPresent()) {
            Customers newCustomer = currentCustomer.get();
            if (updatedCustomer.getCustomerName() != null) {
                newCustomer.setCustomerName(updatedCustomer.getCustomerName());
            }
            if (updatedCustomer.getContactFirstName() != null) {
                newCustomer.setContactFirstName(updatedCustomer.getContactFirstName());
            }
            if (updatedCustomer.getContactLastName() != null) {
                newCustomer.setContactLastName(updatedCustomer.getContactLastName());
            }
            if (updatedCustomer.getPhoneNumber() != null) {
                newCustomer.setPhoneNumber(updatedCustomer.getPhoneNumber());
            }
            if (updatedCustomer.getAddress() != null) {
                newCustomer.setAddress(updatedCustomer.getAddress());
            }
            if (updatedCustomer.getCity() != null) {
                newCustomer.setAddress(updatedCustomer.getAddress());
            }
            if(updatedCustomer.getPostalCode() != null) {
                newCustomer.setPostalCode(updatedCustomer.getPostalCode());
            }
            if (updatedCustomer.getCountry() != null) {
                updatedCustomer.setCountry(updatedCustomer.getCountry());
            }

            newCustomer.setCustomerNumber(id);
            customersRepository.save(newCustomer);
            return true;
        }
        return false;
    }

}
