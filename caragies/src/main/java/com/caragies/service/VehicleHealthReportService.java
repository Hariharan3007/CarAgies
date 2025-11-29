package com.caragies.service;

import org.springframework.stereotype.Service;

import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.VehicleHealthReport;
import com.caragies.repositories.CarRepository;
import com.caragies.repositories.HealthReport;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VehicleHealthReportService {

    private final HealthReport reportRepository;
    private final CarRepository car;
    private final VehicleHealthReportEmailService emailService;

    public VehicleHealthReport createReport(VehicleHealthReport report) {
        VehicleHealthReport saved = reportRepository.save(report);
        emailService.sendReportEmail(saved);   // send immediately
        return saved;
    }

    public void addReport(VehicleHealthReport detail,Integer id) {
       Car carid=car.findById(id).get();
       detail.setCar(carid);
        reportRepository.save(detail);
    }
}
