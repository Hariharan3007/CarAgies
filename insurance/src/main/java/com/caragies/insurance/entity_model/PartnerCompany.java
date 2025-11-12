package com.caragies.insurance.entity_model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer company_id;
    private String company_name;
    private String proprietor_name;
    private String shopNo;
    private String streetName;
    private String city;
    private String district;
    private Long pinCode;
}
