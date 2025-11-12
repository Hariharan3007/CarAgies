package com.caragies.insurance.entity_model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer request_id;

    @ManyToOne
    private CustomerEntity customer_id;

    private String description;

    @Column(columnDefinition = "varchar(15) default='pending'")
    private String status="pending";

    private LocalDate requestDate=LocalDate.now();

    private LocalTime requestTime=LocalTime.now();

    private String location;
}
