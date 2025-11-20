package com.caragies.controller;

import com.caragies.entitydto.CarDto;
import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitydto.UserDto;
import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.entitymodel.Users;
import com.caragies.security.JwtUtil;
import com.caragies.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private JwtUtil jwtUtil;

    public String token = null;

    public UserController(JwtUtil jwtUtil,UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody Users user){
        return userService.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user){
        token = userService.login(user);
        return token;
    }

    @GetMapping("/profile/view")
    public UserDto viewProfile(){
        return userService.viewProfile(getUsername());
    }

    @PostMapping("car/add")
    public String addMyCar(@RequestBody Car car){
        return userService.addMyCar(car, getUsername());
    }

    @GetMapping("car/view")
    public List<CarDto> viewMyCars(){
        return userService.viewMyCars(getUsername());
    }

    @PostMapping("/car/request/{id}")
    public String newRequest(@RequestBody ServiceRequest serviceRequest,  @PathVariable Integer id){
        userService.newRequest(serviceRequest, getUsername(), id);
        return "request submitted";
    }

    @GetMapping("/car/request/view")
    public List<ServiceRequestDto> viewAllRequest(){
       return userService.viewAllRequest(getUsername());
    }

    private String getUsername(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
}
