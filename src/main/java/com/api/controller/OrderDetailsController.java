package com.api.controller;

import com.api.dto.OrderDetailsRequest;
import com.api.repository.OrderDetailsRepository;
import com.api.repository.entity.Customers;
import com.api.repository.entity.OrderDetails;
import com.api.service.OrderDetailsService;
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
public class OrderDetailsController {

    private OrderDetailsRepository orderDetailsRepository;
    private OrderDetailsService orderDetailsService;
    private ModelMapper modelMapper;

    @GetMapping("/orderdetails/{id}")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable long id) {
        Optional<OrderDetails> result = orderDetailsRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orderdetails")
    public void addOrderDetails(@Valid @RequestBody OrderDetailsRequest orderDetails) {
        OrderDetails newOrderDetails = modelMapper.map(orderDetails, OrderDetails.class);
        orderDetailsRepository.save(newOrderDetails);
    }

    @DeleteMapping("/orderdetails/{id}")
    public ResponseEntity<?> deleteOrderDetails(@PathVariable long id) {
        if (orderDetailsRepository.findById(id).isPresent()) {
            orderDetailsRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/orderdetails/{id}")
    public ResponseEntity<?> updateOrderDetails(@PathVariable Long id, @RequestBody OrderDetails orderDetails) {
        if (orderDetailsService.updateOrderDetails(id, orderDetails)) {
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
