package com.caragies.service;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.ServiceRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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

}
