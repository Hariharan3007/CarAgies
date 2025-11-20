package com.caragies.controller;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitymodel.Users;
import com.caragies.service.VendorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private VendorService vendorService;

    public String token = null;

    public VendorController(VendorService vendorService){
        this.vendorService = vendorService;
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        this.token = vendorService.login(user);
        return token;
    }


    @GetMapping("/request/view")
    public List<ServiceRequestDto> viewAllRequest(){
        return vendorService.viewAllRequest();
    }
}
