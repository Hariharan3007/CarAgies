package com.caragies.entitydto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDto {
    private Integer id;
    private String vin; // vehicle identification number
    private String make;
    private String model;
    private Integer yearOfManufacture;
    private String owner;
}
