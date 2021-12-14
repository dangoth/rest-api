package com.api.dtomapper;

import com.api.dto.OrdersResponse;
import com.api.repository.OrdersRepository;
import com.api.repository.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdersMapper {

    @Autowired
    private OrdersRepository ordersRepository;

    public OrdersResponse convertToOrdersDTO(Orders order) {
        OrdersResponse response = new OrdersResponse();
        response.setOrderId(order.getOrderId());
        response.setOrderDate(order.getOrderDate());
        response.setStatusEnum(order.getStatusEnum());
        response.setCustomer(order.getCustomer());
        return response;
    }

}
