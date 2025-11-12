package com.caragies.insurance.service;

import com.caragies.insurance.entity_model.*;
import com.caragies.insurance.repository.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService {

    private AdminRepository adminRepository;
    private CustomerRepository customerRepository;
    private ServicerRepository servicerRepository;
    private PartnersRepository partnersRepository;
    private RequestRepository requestRepository;


    public Optional<AdminEntity> login(String userName, String password) {
        return adminRepository.findByUserNameAndPassword(userName, password);
    }

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<ServicerEntity> getAllServicers() {
        return servicerRepository.findAll();
    }

    public Optional<AdminEntity> viewProfile(Integer id) {
        return adminRepository.findById(id);
    }

    public String deleteCustomerById(Integer id) {
        customerRepository.deleteById(id);
        return "deleted successfully";
    }

    public String deleteServicerById(Integer id) {
        servicerRepository.deleteById(id);
        return "deleted successfully";
    }

    public List<PartnerCompany> getAllPartnerCompanies() {
        return partnersRepository.findAll();
    }

    public String deletePartnerById(Integer id) {
        partnersRepository.deleteById(id);
        return "deleted successfully";
    }

    public List<CustomerRequest> getAllRequests() {
       return requestRepository.findAll();
    }

    public List<CustomerRequest> getAllPendingRequests() {
        return requestRepository.findByStatus(false);
    }

    public List<CustomerRequest> getAllCompletedRequest() {
        return requestRepository.findByStatus(true);
    }

    public void signup(AdminEntity admin) {
        adminRepository.save(admin);
    }
}
