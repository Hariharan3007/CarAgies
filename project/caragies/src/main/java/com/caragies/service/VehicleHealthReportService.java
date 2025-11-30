package com.caragies.service;

import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.VehicleHealthReport;
import com.caragies.repositories.CarRepo;
import com.caragies.repositories.HealthReport;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VehicleHealthReportService {

    private HealthReport reportRepository;
    private CarRepo car;
    private VehicleHealthReportEmailService emailService;

    public VehicleHealthReport createReport(VehicleHealthReport report) {
        VehicleHealthReport saved = reportRepository.save(report);
        emailService.sendReportEmail(saved);   // send immediately
        return saved;
    }

    public void addReport(VehicleHealthReport detail,Integer id) {
       Optional<Car> carid=car.findById(id);
       if(carid.isPresent()){
           System.out.println("car is present" + id);
           detail.setCar(carid.get());
           reportRepository.save(detail);
       }
    }
}
