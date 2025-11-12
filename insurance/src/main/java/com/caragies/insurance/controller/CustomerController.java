package com.caragies.insurance.controller;

import com.caragies.insurance.entity_model.CustomerEntity;
import com.caragies.insurance.entity_model.CustomerRequest;
import com.caragies.insurance.entity_model.LatitudeLangitude;
import com.caragies.insurance.entity_model.VehicleInfoEntity;
import com.caragies.insurance.service.CustomerService;
import com.caragies.insurance.service.VehicleService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    @PostMapping("/signup")
    public ResponseEntity<String> createNewUser(@RequestBody CustomerEntity customer){
        String status=customerService.createNewUser(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody CustomerEntity customer, HttpSession session){
        Optional<CustomerEntity> checkCustomer=customerService.login(customer);
        if(checkCustomer.isPresent()){
            CustomerEntity validCustomer=checkCustomer.get();
            session.setAttribute("customer_id",validCustomer.getCustomer_id());
            session.setAttribute("customer_name",validCustomer.getCustomer_name());
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add_vehicle")
    public ResponseEntity<String> addNewVehicle(@RequestBody VehicleInfoEntity vehicle, HttpSession session){
        Integer id=(Integer) session.getAttribute("customer_id");
        customerService.addNewVehicle(vehicle, id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    // This method is to get all the particular vehicles of the current session owner
    @GetMapping("/getAllVehicle")
    public List<VehicleInfoEntity> getAllVehicle(HttpSession session){
        Integer id=(Integer) session.getAttribute("customer_id");
        return customerService.getAllVehicle(id);
    }

    @PostMapping("/delete_vehicle/{id}")
    public String deleteVehicleById(@PathVariable Integer id){
        return customerService.deleteVehicleById(id);
    }

//    @PostMapping("/subscription")
//    public String toActiveSubscription(){
//
//    }




    @PostMapping("/request")
    public ResponseEntity<String> sendRequest(@RequestBody CustomerRequest request, HttpSession session){
        Integer id=(Integer)session.getAttribute("customer_id");
        customerService.sendRequest(request,id);
        return ResponseEntity.ok("Request Created");
    }




    @GetMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session){
        session.invalidate();
        return ResponseEntity.ok("logout successfully");
    }

}
