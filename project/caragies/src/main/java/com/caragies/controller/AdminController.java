package com.caragies.controller;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.repositories.ServiceRequestRepository;
import com.caragies.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    @GetMapping("/view-request")
    public List<ServiceRequest> viewRequest()
    {
      return adminService.getRequest();
    }
    public ResponseEntity<String> removeUser(@RequestParam String userName)
    {
        return ResponseEntity.ok(adminService.removeUser(userName));
    }
}
