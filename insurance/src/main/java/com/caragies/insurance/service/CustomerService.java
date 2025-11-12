package com.caragies.insurance.service;

import com.caragies.insurance.entity_model.CustomerEntity;
import com.caragies.insurance.entity_model.CustomerRequest;
import com.caragies.insurance.entity_model.LatitudeLangitude;
import com.caragies.insurance.entity_model.VehicleInfoEntity;
import com.caragies.insurance.repository.CustomerRepository;
import com.caragies.insurance.repository.CustomerRequestRepository;
import com.caragies.insurance.repository.VehicleRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerService {

    private CustomerRepository customerRepository;
    private VehicleRepository vehicleRepository;
    private CustomerRequestRepository customerRequestRepository;

    public String createNewUser(CustomerEntity customer){
        CustomerEntity savedCustomer=customerRepository.save(customer);
        return "New Customer Added";
    }

    public List<CustomerEntity> getAllUsers() {
       return customerRepository.findAll();
    }

    public void addNewVehicle(VehicleInfoEntity vehicle, Integer customer_id) {
        CustomerEntity customer=customerRepository.findById(customer_id).get();
        vehicle.setCustomer(customer);
        vehicleRepository.save(vehicle);
    }

    public Optional<CustomerEntity> login(CustomerEntity customer) {
//        return customerRepository.findByCustomer_userName(userName);
        return customerRepository.findById(customer.getCustomer_id());
    }

    public List<VehicleInfoEntity> getAllVehicle(Integer id) {
        return vehicleRepository.findAllByCustomerId(id);
    }

    public String deleteVehicleById(Integer id) {
        vehicleRepository.deleteById(id);
        return "deleted successful";
    }

    public void sendRequest(CustomerRequest request, Integer id) {
            String location=getLocation();
            request.setLocation(location);
            request.setCustomer_id(customerRepository.findById(id).get());
            customerRequestRepository.save(request);
    }
    public String getLocation() {
        String url = "https://ipinfo.io/json"; // Open API (no key required for small usage)
        RestTemplate restTemplate=new RestTemplate();
        LatitudeLangitude latitudeLangitude  = restTemplate.getForObject(url,LatitudeLangitude.class);
        System.out.println(latitudeLangitude);
        String tem=latitudeLangitude.getLoc();
        System.out.println(tem);
        String parts[]= tem.split(",");
        String  response= getAddress(Double.parseDouble(parts[0]),Double.parseDouble(parts[1]));

        try{
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            String displayName = root.path("display_name").asText();
            return displayName != null ? displayName : "Address not found";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error fetching address";
        }
    }
    public String getAddress(double lat, double lon) {
        String url = "https://nominatim.openstreetmap.org/reverse?lat=" + lat + "&lon=" + lon + "&format=json";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}
