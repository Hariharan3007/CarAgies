package com.caragies.repositories;

import com.caragies.entitymodel.VehicleHealthReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface HealthReport extends JpaRepository<VehicleHealthReport,Long> {
    List<VehicleHealthReport> findByReportDateBetween(LocalDateTime start, LocalDateTime end);
}
