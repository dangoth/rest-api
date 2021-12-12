package com.api.controller;

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

@RestController
@AllArgsConstructor
public class CustomersController {

    private CustomersRepository customersRepository;
    private CustomersService customersService;
    private ModelMapper modelMapper;

    @GetMapping("/customers")
    public List<Customers> getAllCustomers() {
        return customersService.getAllCustomers();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customers> getCustomer(@PathVariable long id) {
        Optional<Customers> result = customersRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
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
    public Map<String, String> handleValidationExceptions(
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
