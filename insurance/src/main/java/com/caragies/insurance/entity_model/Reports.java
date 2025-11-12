package com.caragies.insurance.entity_model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Reports {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer report_id;
//    @OneToOne
//    private CustomerEntity customer;
//    @OneToOne
//    private ServicerEntity servicer;

//    private LocalDate reportDate;

}
