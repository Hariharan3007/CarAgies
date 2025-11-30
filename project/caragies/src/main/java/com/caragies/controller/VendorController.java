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

    @GetMapping("/request/viewById/{id}")
    public ServiceRequestDto viewRequestById(@PathVariable Integer id){
        return vendorService.viewRequestById(id);
    }

    @PostMapping("/requests/{id}/accept")
    public String acceptRequest(@PathVariable Integer id, @RequestBody String scheduled){
        return vendorService.acceptRequest(id, scheduled);
    }

    @GetMapping("/view/scheduled/requests")
    public List<ServiceRequestDto> viewScheduledRequest(){
        return vendorService.viewScheduledRequests();
    }

    @GetMapping("view/process/requests")
    public List<ServiceRequestDto> viewLiveRequest(){
        return vendorService.viewLiveRequest();
    }

    @GetMapping("request/process/{id}")
    public void processRequestById(@PathVariable Integer id){
        System.out.println("controller hit");
        System.out.println("id " + id);
        vendorService.processRequestById(id);
    }

    @GetMapping("request/complete/{id}")
    public String completeRequest(@PathVariable Integer id){

        return vendorService.completeRequest(id);
    }

   @PostMapping("/healthreport")
   public String healthreport(@RequestBody VehicleHealthReport detail,@RequestParam Integer id)
    {
        System.out.println(id);
        report.addReport(detail,id);
        return "Successfully added";
    }
}
