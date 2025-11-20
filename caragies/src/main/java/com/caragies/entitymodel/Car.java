package com.caragies.entitymodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String vin;

    private String make;

    private String model;

    private String fuelType;

    private Integer yearOfManufacture;

    @ManyToOne
    private Users users;

}
