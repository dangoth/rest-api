package com.api.repository;

import com.api.dto.CustomersResponse;
import com.api.repository.entity.Customers;
import com.api.repository.entity.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

    public List<Customers> findAllByCity(String city);

}
