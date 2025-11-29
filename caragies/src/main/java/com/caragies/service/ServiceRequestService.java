package com.caragies.service;

import org.springframework.stereotype.Service;

import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.CarRepository;
import com.caragies.repositories.ServiceRequestRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;
@Service
@Data
@RequiredArgsConstructor
public class ServiceRequestService {
  private  final ServiceRequestRepository serviceRequestRepository;
  private final CarRepository carRepo;
    public void serviceRequest(ServiceRequest serviceRequest) {
        Car car;
        if (serviceRequest == null) {
            throw new RuntimeException("serviceRequest is null");
        }
        if (serviceRequest.getCar() == null) {
            throw new RuntimeException("Car is null inside serviceRequest");
        }
        else {
            Integer id=serviceRequest.getCar().getId();
           car= carRepo.findById(id).get();
        }
        if (car.getUsers() == null) {
            throw new RuntimeException("Users object is null in Car");
        }
        Integer id = car.getUsers().getId();
        serviceRequest.setUser(Users.builder().id(id).build());
        serviceRequestRepository.save(serviceRequest);
    }
}
