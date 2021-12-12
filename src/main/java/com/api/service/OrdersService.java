package com.api.service;

import com.api.repository.OrdersRepository;
import com.api.repository.entity.Orders;
import com.api.repository.entity.StatusEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrdersService {

    private OrdersRepository ordersRepository;

    private Orders addOrder(LocalDate orderDate, StatusEnum statusEnum, int customerNumber) {
        Orders order = Orders.builder()
                .orderDate(orderDate)
                .statusEnum(statusEnum)
                .customerNumber(customerNumber)
                .build();
        return ordersRepository.save(order);
    }

    public boolean updateOrder(long id, Orders updatedOrder) {
        Optional<Orders> currentOrder = ordersRepository.findById(id);
        if (currentOrder.isPresent()) {
            Orders newOrder = currentOrder.get();

            if (updatedOrder.getOrderDate() != null) {
                newOrder.setOrderDate(updatedOrder.getOrderDate());
            }
            if (updatedOrder.getStatusEnum() != null) {
                newOrder.setStatusEnum(updatedOrder.getStatusEnum());
            }
            if (updatedOrder.getCustomerNumber() > 0) {
                newOrder.setCustomerNumber(updatedOrder.getCustomerNumber());
            }
            newOrder.setOrderId(id);
            ordersRepository.save(newOrder);
            return true;
        }
        return false;
    }
}
