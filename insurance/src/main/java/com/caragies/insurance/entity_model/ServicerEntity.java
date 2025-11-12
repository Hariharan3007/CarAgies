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
public class ServicerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer servicer_id;

    private String servicer_name;

    private String role="Servicer";

    private LocalDate joiningDate=LocalDate.now();

}
