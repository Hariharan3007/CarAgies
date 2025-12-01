package com.caragies.controller;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitydto.UserDto;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.entitymodel.Users;
import com.caragies.service.AdminService;
import com.caragies.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;
    private UserService userService;
    @PostMapping("/login")
    public String admiLogin(@RequestBody Users users)
    {
      return   userService.login(users);
    }
    @GetMapping("view/user")
    public  ResponseEntity<List<UserDto>> viewUser()
    {
        return ResponseEntity.ok(adminService.findUser());
    }
    @GetMapping("view/vendor")
    public  ResponseEntity<List<UserDto>> viewVendor()
    {
        return ResponseEntity.ok(adminService.findVendor());
    }
    @GetMapping("/view-request")
    public List<ServiceRequest> viewRequest()
    {
      return adminService.getRequest();
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> removeUser(@RequestParam String userName)
    {
        return ResponseEntity.ok(adminService.removeUser(userName));
    }
    @GetMapping("status/request")
    public ResponseEntity<List<ServiceRequestDto>> pendingRequest()
    {
        return ResponseEntity.ok( adminService.requestPending());
    }
    @GetMapping("status/scheduled")
    public ResponseEntity<List<ServiceRequestDto>> scheduledRequest()
    {
        return ResponseEntity.ok( adminService.requestScheduled());
    }
    @GetMapping("status/process")
    public ResponseEntity<List<ServiceRequestDto>> progressRequest()
    {
        return ResponseEntity.ok( adminService.requestInProcess());
    }
    @GetMapping("status/completed")
    public ResponseEntity<List<ServiceRequestDto>> scheduledCompleted()
    {
        return ResponseEntity.ok( adminService.requestCompleted());
    }

    @GetMapping("getAllStatus")
    public Map<String,Long> getAllRequests(){
        return adminService.getAllRequests();
    }
}
