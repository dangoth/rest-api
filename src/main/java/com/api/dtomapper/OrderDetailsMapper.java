package com.api.dtomapper;

import com.api.dto.OrderDetailsResponse;
import com.api.repository.OrderDetailsRepository;
import com.api.repository.entity.OrderDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailsMapper {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public OrderDetailsResponse convertToOrderDetailsDTO(OrderDetails orderDetails) {
        OrderDetailsResponse response = new OrderDetailsResponse();
        response.setOrderNumber(orderDetails.getOrderNumber());
        response.setProductId(orderDetails.getProductId());
        response.setQuantityOrdered(orderDetails.getQuantityOrdered());
        response.setPriceEach(orderDetails.getPriceEach());
        return response;
    }

}
