package com.caragies.insurance.service;

import com.caragies.insurance.entity_model.VehicleInfoEntity;
import com.caragies.insurance.repository.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VehicleService {
    private VehicleRepository vehicleRepository;
    public List<VehicleInfoEntity> getAllVehicle(Integer id) {
        return vehicleRepository.findAllByCustomerId(id);
    }
}
