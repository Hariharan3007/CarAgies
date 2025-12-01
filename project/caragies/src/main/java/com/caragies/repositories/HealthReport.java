package com.caragies.repositories;

import com.caragies.entitymodel.VehicleHealthReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository

public interface HealthReport extends JpaRepository<VehicleHealthReport,Long> {
    List<VehicleHealthReport> findByReportDateBetween(LocalDateTime start, LocalDateTime end);
}
