package com.caragies.service;
import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitydto.UserDto;
import com.caragies.entitymodel.ServiceRequest;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.ServiceRequestRepository;
import com.caragies.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminService {
    private ServiceRequestRepository serviceRequestRepository;
    private UserRepository userRepository;

    public List<ServiceRequest> getRequest() {
        return serviceRequestRepository.findAll();
    }

    public String removeUser(String userName)
    {
        Optional<Users> user=userRepository.findByUsername(userName);
        if(user.isEmpty()){
            return "invalid User Name";
        }
        userRepository.delete(user.get());
        return "Removed successfully";

    }


    public List<UserDto> findUser() {
        List<Users> user= userRepository.findByRoleUser();
        return user.stream().map(u->new UserDto(u.getId(),u.getName(),u.getUsername(),u.getEmail(),u.getPhone())).collect(Collectors.toList());
    }

    public List<UserDto> findVendor() {
        List<Users> user=userRepository.findByRoleVendor();
        return user.stream().map(u->new UserDto(u.getId(),u.getName(),u.getUsername(),u.getEmail(),u.getPhone())).collect(Collectors.toList());
    }

    public List<ServiceRequestDto> requestPending() {
        List<ServiceRequest> dto = serviceRequestRepository.findByRequestedStatus();
        return dto.stream().map(entity -> ServiceRequestDto.builder()
                .id(entity.getId())
                .carVin(entity.getCar().getVin())
                .car_id(entity.getCar().getId())
                .requester(entity.getUser().getName())
                .description(entity.getDescription())
                .requestedAt(entity.getRequestedAt())
                .scheduledAt(entity.getScheduledAt())
                .completedAt(entity.getCompletedAt())
                .status(entity.getStatus())
                .location(entity.getLocation())
                .estimatedCost(entity.getEstimatedCost())
                .finalCost(entity.getFinalCost())
                .build()).collect(Collectors.toList());
    }
    public List<ServiceRequestDto> requestScheduled() {
        List<ServiceRequest> dto = serviceRequestRepository.findByScheduledStatus();
        return dto.stream().map(entity -> ServiceRequestDto.builder()
                .id(entity.getId())
                .carVin(entity.getCar().getVin())
                .car_id(entity.getCar().getId())
                .requester(entity.getUser().getName())
                .description(entity.getDescription())
                .requestedAt(entity.getRequestedAt())
                .scheduledAt(entity.getScheduledAt())
                .completedAt(entity.getCompletedAt())
                .status(entity.getStatus())
                .location(entity.getLocation())
                .estimatedCost(entity.getEstimatedCost())
                .finalCost(entity.getFinalCost())
                .build()).collect(Collectors.toList());
    }
    public List<ServiceRequestDto> requestInProcess() {
        List<ServiceRequest> dto = serviceRequestRepository.findByProcessStatus();
        return dto.stream().map(entity -> ServiceRequestDto.builder()
                .id(entity.getId())
                .carVin(entity.getCar().getVin())
                .car_id(entity.getCar().getId())
                .requester(entity.getUser().getName())
                .description(entity.getDescription())
                .requestedAt(entity.getRequestedAt())
                .scheduledAt(entity.getScheduledAt())
                .completedAt(entity.getCompletedAt())
                .status(entity.getStatus())
                .location(entity.getLocation())
                .estimatedCost(entity.getEstimatedCost())
                .finalCost(entity.getFinalCost())
                .build()).collect(Collectors.toList());
    }

    public List<ServiceRequestDto> requestCompleted() {
        List<ServiceRequest> dto = serviceRequestRepository.findByCompiledStatus();
        return dto.stream().map(entity -> ServiceRequestDto.builder()
                .id(entity.getId())
                .carVin(entity.getCar().getVin())
                .car_id(entity.getCar().getId())
                .requester(entity.getUser().getName())
                .description(entity.getDescription())
                .requestedAt(entity.getRequestedAt())
                .scheduledAt(entity.getScheduledAt())
                .completedAt(entity.getCompletedAt())
                .status(entity.getStatus())
                .location(entity.getLocation())
                .estimatedCost(entity.getEstimatedCost())
                .finalCost(entity.getFinalCost())
                .build()).collect(Collectors.toList());
    }

    public Map<String, Long> getAllRequests() {
            return serviceRequestRepository.findAll().stream()
                    .collect(Collectors.groupingBy(a->  a.getStatus(), Collectors.counting()));

    }


}

