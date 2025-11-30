package com.caragies.service;

import com.caragies.entitymodel.Car;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.CarRepo;
import com.caragies.repositories.ServiceRequestRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import com.caragies.entitymodel.ServiceRequest;
@Service
@Data
@AllArgsConstructor
public class ServiceRequestService {
  private   ServiceRequestRepository serviceRequestRepository;
  private CarRepo carRepo;
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
