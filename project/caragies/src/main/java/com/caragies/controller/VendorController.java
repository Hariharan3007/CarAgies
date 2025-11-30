package com.caragies.controller;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitymodel.Users;
import com.caragies.entitymodel.VehicleHealthReport;
import com.caragies.service.VehicleHealthReportService;
import com.caragies.service.VendorService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
@Data
@AllArgsConstructor
public class VendorController {

    private VendorService vendorService;
    private VehicleHealthReportService report;
    @PostMapping("/login")
    public String login(@RequestBody Users user){

        return vendorService.login(user);
    }


    @GetMapping("/request/view")
    public List<ServiceRequestDto> viewAllRequest(){
        return vendorService.viewAllRequest();
    }
    @PostMapping("/healthreport")
   public String healthreport(@RequestBody VehicleHealthReport detail,@RequestParam Integer id)
    {
        report.addReport(detail,id);
        return "Successfully added";
    }
}
