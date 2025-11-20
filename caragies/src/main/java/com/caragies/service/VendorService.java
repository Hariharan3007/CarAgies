package com.caragies.service;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.ServiceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class VendorService {

    private UserService userService;

    private ServiceRequestRepository serviceRequestRepository;

    public String login(Users user){
        return userService.login(user);
    }

    public List<ServiceRequestDto> viewAllRequest(){
        return serviceRequestRepository.findByRequestedStatus().stream()
                .map(serviceRequest ->
                        new ServiceRequestDto(serviceRequest.getId(),
                                serviceRequest.getCar().getVin(),
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
