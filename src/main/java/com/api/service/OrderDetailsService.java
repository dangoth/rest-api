package com.api.service;

import com.api.repository.OrderDetailsRepository;
import com.api.repository.entity.OrderDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderDetailsService {

    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetails addOrderDetails(int productId, int quantityOrdered, double priceEach) {
        OrderDetails orderDetails = OrderDetails.builder()
                .productId(productId)
                .quantityOrdered(quantityOrdered)
                .priceEach(priceEach)
                .build();
        return orderDetailsRepository.save(orderDetails);
    }

    public boolean updateOrderDetails (long id, OrderDetails updatedOrderDetails) {
        Optional<OrderDetails> currentOrderDetails = orderDetailsRepository.findById(id);
        if (currentOrderDetails.isPresent()) {
            OrderDetails newOrderDetails = currentOrderDetails.get();

            if (updatedOrderDetails.getProductId() > 0) {
                newOrderDetails.setProductId(updatedOrderDetails.getProductId());
            }
            if (updatedOrderDetails.getQuantityOrdered() > 0) {
                newOrderDetails.setQuantityOrdered(updatedOrderDetails.getQuantityOrdered());
            }
            if (updatedOrderDetails.getPriceEach() > 0) {
                newOrderDetails.setPriceEach(updatedOrderDetails.getPriceEach());
            }
            newOrderDetails.setOrderNumber(id);
            orderDetailsRepository.save(newOrderDetails);
            return true;
        }
        return false;
    }

}
