package com.api.controller;

import com.api.dto.CustomersResponse;
import com.api.dtomapper.CustomerMapper;
import com.api.repository.CustomersRepository;
import com.api.repository.entity.Customers;
import com.api.service.CustomersService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class CustomersController {

    private CustomersRepository customersRepository;
    private CustomersService customersService;
    private CustomerMapper customerMapper;
    private ModelMapper modelMapper;

    @GetMapping("/customers")
    public List<CustomersResponse> getAllCustomers() {
        List<Customers> customers = customersRepository.findAll();
        return customers.stream()
                .map(customer -> customerMapper.convertToCustomerDTO(customer))
                .collect(Collectors.toList());
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomersResponse> getCustomer(@PathVariable long id) {
        Optional<Customers> result = customersRepository.findById(id);
        return result.map(customers -> new ResponseEntity<>(customerMapper.convertToCustomerDTO(customers), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/customers")
    public void addCustomer(@Valid @RequestBody Customers customer) {
        Customers newCustomer = modelMapper.map(customer, Customers.class);
        customersRepository.save(newCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
        if (customersRepository.findById(id).isPresent()) {
            customersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/customers/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable long id, @RequestBody Customers customer) {
        if (customersService.updateCustomer(id, customer)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions (
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
