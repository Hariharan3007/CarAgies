package com.caragies.controller;
import com.caragies.entitydto.CarDto;
import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitydto.UserDto;
import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.service.ServiceRequestService;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.UserRepository;
import com.caragies.security.JwtUtil;
import com.caragies.service.UserService;
import com.caragies.service.VerificationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

    private UserService userService;
    private JwtUtil jwtUtil;
    private  UserRepository userRepo;
    private ServiceRequestService request;
    private VerificationService verificationService;
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public String signup(@RequestBody Users user){
        return userService.signup(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return   userService.login(user);
    }
    @GetMapping("/profile/view")
    public UserDto viewProfile(){
        return userService.viewProfile(getUsername());
    }
    @PostMapping("/car/add")
    public String addMyCar(@RequestBody Car car){
        return userService.addMyCar(car, getUsername());
    }

    @GetMapping("/car/view")
    public List<CarDto> viewMyCars()
    {
        return userService.viewMyCars(getUsername());
    }

    @PostMapping("/car/request/{id}")
    public String newRequest(@RequestBody ServiceRequest serviceRequest, @PathVariable Integer id)
    {
        userService.newRequest(serviceRequest, getUsername(), id);
        return "request submitted";
    }

    @GetMapping("/car/request/view")
    public List<ServiceRequestDto> viewAllRequest()
    {
       return userService.viewAllRequest(getUsername());
    }

    private String getUsername(){
        var auth = SecurityContextHolder.getContext().getAuthentication();
               return auth.getName();
        }

    @GetMapping("/otp")
    public String optsent(@RequestParam String email) {
       // String email=userRepo.findFirstByEmailOrderByIdDesc(email)
        verificationService.createAndSendCode(email);
        return "successfully mail otp sent to the " + email;
    }
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCodeAndIssueToken(@RequestBody Map<String,String> body) {
        String email = body.get("email");
        String code = body.get("code");
        String newPassword=body.get("newPassword");
        if (email == null || code == null) {
            return ResponseEntity.badRequest().body(Map.of("error","email and code required"));
        }
        boolean ok = verificationService.verifyCode(email, code);
        if (!ok) {
            return ResponseEntity.status(401).body(Map.of("error","invalid or expired code"));
        }
        var userOpt = userRepo.findFirstByEmailOrderByIdDesc(email);
        if (userOpt.isEmpty()) return ResponseEntity.status(404).body(Map.of("error","user not found"));
        Users user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
        String jwt = jwtUtil.createToken(user.getUsername(),user.getRole());
        return ResponseEntity.ok(Map.of("token", jwt));
    }

}

