package com.api.controller;

import com.api.dto.OrdersRequest;
import com.api.dto.OrdersResponse;
import com.api.dtomapper.OrdersMapper;
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
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
public class OrdersController {

    private OrdersRepository ordersRepository;
    private OrdersService ordersService;
    private OrdersMapper ordersMapper;
    private ModelMapper modelMapper;

    @GetMapping("/orders")
    public List<OrdersResponse> getAllOrders() {
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(order -> ordersMapper.convertToOrdersDTO(order))
                .collect(Collectors.toList());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrdersResponse> getOrder(@PathVariable long id) {
        Optional<Orders> result = ordersRepository.findById(id);
        return result.map(orders -> new ResponseEntity<>(ordersMapper.convertToOrdersDTO(orders), HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
