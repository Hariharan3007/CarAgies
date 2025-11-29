package com.caragies.entitydto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Accessorydto {

    private Long id;
    private String accessoryName;
    private String description;
    private Double price;
    private Integer stockQuantity;
}



