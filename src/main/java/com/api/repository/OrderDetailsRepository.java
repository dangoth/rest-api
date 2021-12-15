package com.api.repository;

import com.api.repository.entity.OrderDetails;
import com.api.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Long> {

}
