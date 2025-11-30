package com.caragies.service;

import com.caragies.entitydto.CarDto;
import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitydto.UserDto;
import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.entitymodel.Users;
import com.caragies.location.LocationAccess;
import com.caragies.repositories.CarRepo;
import com.caragies.repositories.ServiceRequestRepository;
import com.caragies.repositories.UserRepository;
import com.caragies.security.JwtUtil;
import com.caragies.service_interface.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    private CarRepo carRepository;

    private LocationAccess locationAccess;

    private ModelMapper mapper;

    private ServiceRequestRepository serviceRequestRepository;


    @Override
    public String signup(Users user) {
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        // Set default role if not provided
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        try {
            Users isUser = userRepository.save(user);
            return "successfully created";
        }catch (Exception e){
            return "username already exists try with different username";
        }
    }

    @Override
    public String login(Users user) {
        String username = user.getUsername();
        String password = user.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication auth = authenticationManager.authenticate(token);
        try {
            if (auth.isAuthenticated()) {
                String role = userRepository.findByUsername(username).get().getRole();
                return jwtUtil.createToken(username, role);
            }
        }catch (Exception e){
            return "Invalid Credentials";
        }
        return "Invalid Credentials";
    }

    public UserDto viewProfile(String username) {
        Users user = getUser(username);
        return mapper.map(user, UserDto.class);
    }

    public String addMyCar(Car car, String username) {
        Optional<Car> isCarAvailable = carRepository.findByVin(car.getVin());
        if(isCarAvailable.isPresent()){
            return "Check with Registration Number";
        }
        car.setUsers(getUser(username));
        carRepository.save(car);
        return "Car added successfully";
    }

    public List<CarDto> viewMyCars(String username) {
        Integer id = getUser(username).getId();

        return carRepository.findByUsersId(id).stream().map(car -> new CarDto(
                car.getId(),
                car.getVin(),
                car.getMake(),
                car.getMake(),
                car.getYearOfManufacture(),
                car.getUsers().getName()))
                .collect(Collectors.toList());
    }

    private Users getUser(String username){
        return userRepository.findByUsername(username).get();
    }

    public void newRequest(ServiceRequest serviceRequest, String username, Integer carId) {
        String address = locationAccess.getLocation();
        Car car = carRepository.findById(carId).get();
        Users user = userRepository.findByUsername(username).get();
        serviceRequest.setUser(user);
        serviceRequest.setCar(car);
       serviceRequest.setLocation(address);
       serviceRequestRepository.save(serviceRequest);

    }

    public List<ServiceRequestDto> viewAllRequest(String username) {
        Integer id = userRepository.findByUsername(username).get().getId();
        return serviceRequestRepository.findByUserId(id).stream()
                .map(serviceRequest ->
                        new ServiceRequestDto(serviceRequest.getId(),
                                serviceRequest.getCar().getVin(),
                                serviceRequest.getCar().getId(),
                                serviceRequest.getUser().getName(),
                                serviceRequest.getDescription(),
                                serviceRequest.getRequestedAt(),
                                serviceRequest.getScheduledAt(),
                                serviceRequest.getCompletedAt(),
                                serviceRequest.getStatus(),
                                serviceRequest.getLocation(),
                                serviceRequest.getEstimatedCost(),
                                serviceRequest.getFinalCost()))
                .collect(Collectors.toList());
    }


}
