package com.api.controller;

import com.api.dto.OrdersRequest;
import com.api.repository.OrdersRepository;
import com.api.repository.entity.Orders;
import com.api.service.OrdersService;
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
public class OrdersController {

    private OrdersRepository ordersRepository;
    private OrdersService ordersService;
    private ModelMapper modelMapper;

    @GetMapping("/orders")
    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable long id) {
        Optional<Orders> result = ordersRepository.findById(id);
        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/orders")
    public void addOrder(@Valid @RequestBody OrdersRequest order) {
        Orders newOrder = modelMapper.map(order, Orders.class);
        ordersRepository.save(newOrder);
    }

    @DeleteMapping("/orders")
    public ResponseEntity<?> deleteOrder (@PathVariable Long id) {
        if (ordersRepository.findById(id).isPresent()) {
            ordersRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/orders/{id}")
    public ResponseEntity<?> updateOrder (@PathVariable long id, @RequestBody Orders order) {
        if (ordersService.updateOrder(id, order)) {
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
