package com.caragies.insurance.controller;

import com.caragies.insurance.entity_model.*;
import com.caragies.insurance.service.AdminService;
import com.caragies.insurance.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody AdminEntity admin){
        adminService.signup(admin);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password, HttpSession session){
    Optional<AdminEntity> check=adminService.login(userName, password);
    if(check.isPresent()){
        AdminEntity validUser=check.get();
        session.setAttribute("admin_id", validUser.getAdmin_id());
        session.setAttribute("admin_name", validUser.getAdmin_name());
        return new ResponseEntity<>(HttpStatus.OK);
    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/view_profile")
    public ResponseEntity<AdminEntity> viewProfile(HttpSession session){
        Integer id=(Integer) session.getAttribute("admin_id");
       Optional<AdminEntity> checkAdmin= adminService.viewProfile(id);
       if(checkAdmin.isPresent()){
           return ResponseEntity.ok(checkAdmin.get());
       }
       return ResponseEntity.notFound().build();
    }

    @GetMapping("/view_customer")
    public List<CustomerEntity> getAllCustomers(){
        return adminService.getAllCustomers();
    }

    @PostMapping("/delete_customer/{id}")
    public String deleteCustomerById(@PathVariable Integer id){
        return adminService.deleteCustomerById(id);
    }

    @GetMapping("/view_servicer")
    public List<ServicerEntity> getAllServicers(){
        return adminService.getAllServicers();
    }

    @PostMapping("/delete_servicer/{id}")
    public String deleteServicerById(@PathVariable Integer id){
        return adminService.deleteServicerById(id);
    }

    @GetMapping("/view_partners")
    public List<PartnerCompany> getAllPartnerCompany(){
        return adminService.getAllPartnerCompanies();
    }

    @PostMapping("delet_partner/{id}")
    public String deletePartnerById(@PathVariable Integer id){
        return adminService.deletePartnerById(id);
    }

    @GetMapping("/view_requests")
    public List<CustomerRequest> getAllRequest(){
        return adminService.getAllRequests();
    }

    @GetMapping("/view_pending_request")
    public List<CustomerRequest> getAllPendingRequest(){
        return adminService.getAllPendingRequests();
    }

    @GetMapping("/view_completed_request")
    public List<CustomerRequest> getAllCompletedRequest(){
       return adminService.getAllCompletedRequest();
    }
    @GetMapping("/view_reports")
    public List viewAllReports(){
        return new ArrayList();
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("logout");
    }
}
