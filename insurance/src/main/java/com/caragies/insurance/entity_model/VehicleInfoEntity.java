package com.caragies.insurance.entity_model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.Mapping;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "vehicle_id")
public class VehicleInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vehicle_id;

    private String vehicle_manufacturer;

    private String vehicle_model;

    private String vehicle_fuelType;

    private LocalDate vehicle_registeredDate;

    private LocalDate vehicle_manufactureYear;

    private String vehicle_chassisNumber;

    private String vehicle_engineNumber;

    private String vehicleType;

    @ManyToOne
    private CustomerEntity customer;
}
