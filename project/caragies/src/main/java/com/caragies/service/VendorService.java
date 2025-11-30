package com.caragies.service;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.ServiceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
                .map(sr -> {
                    Integer id = sr.getId();
                    String vin = Optional.ofNullable(sr.getCar()).map(c -> c.getVin()).orElse(null);
                    Integer carId = Optional.ofNullable(sr.getCar()).map( carid -> carid.getId()).orElse(null);
                    String userName = Optional.ofNullable(sr.getUser()).map(u -> u.getName()).orElse(null);

                    return new ServiceRequestDto(
                            id, vin, carId, userName,
                            sr.getDescription(),
                            sr.getRequestedAt(),
                            sr.getScheduledAt(),
                            sr.getCompletedAt(),
                            sr.getStatus(),
                            sr.getLocation(),
                            sr.getEstimatedCost(),
                            sr.getFinalCost()
                    );
                })
                .collect(Collectors.toList());
    }

    public String acceptRequest(Integer id, String scheduled) {
        System.out.println("checking request id");
        Optional<ServiceRequest> serviceRequest = serviceRequestRepository.findById(id);
        if(serviceRequest.isPresent()){
            System.out.println("setting schedule");
            ServiceRequest req = serviceRequest.get();
            req.setScheduledAt(LocalDate.parse(scheduled));
            req.setStatus("Scheduled");
            serviceRequestRepository.save(req);
            return "Accepted";
        }
        System.out.println("not found");
        return "Not Found";
    }

    public ServiceRequestDto viewRequestById(Integer id) {
        ServiceRequest req = serviceRequestRepository.findById(id).get();
        return new ServiceRequestDto(req.getId(),
                req.getCar().getVin(),
                req.getCar().getId(),
                req.getUser().getName(),
                req.getDescription(),
                req.getRequestedAt(),
                req.getScheduledAt(),
                req.getCompletedAt(),
                req.getStatus(),
                req.getLocation(),
                req.getEstimatedCost(),
                req.getFinalCost());
    }

    public List<ServiceRequestDto> viewScheduledRequests() {
       return serviceRequestRepository.findByScheduledStatus().stream().map(
               req -> {
                   return new ServiceRequestDto(
                           req.getId(),
                           req.getCar().getVin(),
                           req.getCar().getId(),
                           req.getUser().getName(),
                           req.getDescription(),
                           req.getRequestedAt(),
                           req.getScheduledAt(),
                           req.getCompletedAt(),
                           req.getStatus(),
                           req.getLocation(),
                           req.getEstimatedCost(),
                           req.getFinalCost()
                   );
               })
        .collect(Collectors.toList());
    }
}
