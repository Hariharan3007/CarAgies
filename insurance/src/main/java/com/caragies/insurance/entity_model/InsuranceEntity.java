package com.caragies.insurance.entity_model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer insurance_id;
    private String policy_number;
    private String policy_company;
    private String policy_type; //comprehensive or thrid party
    private Double premium_amount;
    private Double insured_amount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
}
