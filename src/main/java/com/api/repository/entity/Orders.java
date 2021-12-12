package com.api.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "orderId")
    @JoinColumn(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "orderDate")
    private LocalDate orderDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum statusEnum;

    @Column(name = "customerNumber")
    @JoinColumn(name = "order_customerNumber", nullable = false)
    private int customerNumber;

}
