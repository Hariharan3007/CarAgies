package com.caragies.entitymodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class ServiceRequest {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Users user;

    private String description;

    private String status="Requested";

    private LocalDate requestedAt = LocalDate.now();

    private LocalDate scheduledAt;

    private LocalDate completedAt;

    private BigDecimal estimatedCost;

    private BigDecimal finalCost;

    private String location;

}
