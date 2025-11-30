package com.caragies.service;

import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.VehicleHealthReport;
import com.caragies.repositories.CarRepo;
import com.caragies.repositories.HealthReport;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleHealthReportService {

    private final HealthReport reportRepository;
    private CarRepo car;
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
