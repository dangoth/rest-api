package com.api.repository;

import com.api.repository.entity.Customers;
import com.api.repository.entity.Orders;
import com.api.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

    public List<Orders> findAllByCustomer(Customers customer);

}
