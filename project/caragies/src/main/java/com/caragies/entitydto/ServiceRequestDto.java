package com.caragies.entitydto;


import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ServiceRequestDto {

    private Integer id;
    private String carVin;
    private Integer car_id;
    private String requester; // requester
    private String description;
    private LocalDate requestedAt;
    private LocalDate scheduledAt;
    private LocalDate completedAt;
    private String status;
    private String location;
    private BigDecimal estimatedCost;
    private BigDecimal finalCost;

}
