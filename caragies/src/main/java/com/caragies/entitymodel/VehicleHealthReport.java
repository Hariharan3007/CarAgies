package com.caragies.entitymodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "health_reports")
public class VehicleHealthReport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime reportDate;

    @Lob
    private String summary; // short textual summary

    @Lob
    private String advisory; // detailed advice & recommendations

    private Integer tyreScore; // 0-100
    private Integer engineScore;
    private Integer brakesScore;
    private Integer electricalScore;

    private BigDecimal estimatedRepairCost;
}
