package com.caragies.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caragies.entitymodel.Accessory;
import com.caragies.repositories.AccessoryRepository;

@Service
public class Accessoryservice {

    @Autowired
    private AccessoryRepository repository;

    
    public Accessory createAccessory(Accessory accessory) {
        return repository.save(accessory);
    }

    
    public Accessory getAccessoryById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accessory not found with ID: " + id));
    }

    
    public List<Accessory> getAllAccessories() {
        return repository.findAll();
    }

    
    public Accessory updateAccessory(Long id, Accessory accessory) {
        Accessory existing = getAccessoryById(id);

        existing.setAccessoryName(accessory.getAccessoryName());
        existing.setDescription(accessory.getDescription());
        existing.setPrice(accessory.getPrice());
        existing.setStockQuantity(accessory.getStockQuantity());

        return repository.save(existing);
    }

    
    public String deleteAccessory(Long id) {
        repository.deleteById(id);
        return "Accessory deleted successfully";
    }
}
