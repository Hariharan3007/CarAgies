package com.caragies.service;

import com.caragies.entitymodel.ServiceRequest;
import com.caragies.entitymodel.Users;
import com.caragies.repositories.ServiceRequestRepository;
import com.caragies.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
}
