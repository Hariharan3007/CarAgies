package com.caraegis.Accessories.and.Upgrade.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caraegis.Accessories.and.Upgrade.AccessoryController.AccessoryController;
import com.caraegis.Accessories.and.Upgrade.Repository.AccesoryRepository;
import com.caraegis.Accessories.and.Upgrade.accessoryentity.Accessory;

@Service
public class AccessoryService {

	 @Autowired
	    private AccesoryRepository repository;

	    public List<Accessory> getAllAccessories() {
	        return repository.findAll();
	    }

    public List<Accessory> getByCategory(String category) {
        return repository.findByCategory(category);
    }

    public Accessory addAccessory(Accessory accessory) {
        return repository.save(accessory);
    }

    public void deleteAccessory(Long id) {
        repository.deleteById(id);
    }
    public Accessory updatePriceAndStock(Long id, Double price, Integer stockQuantity) {
        Accessory accessory = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Accessory not found with id: " + id));

        if (price != null) {
            accessory.setPrice(price);
        }
        if (stockQuantity != null) {
            accessory.setStockQuantity(stockQuantity);
        }

        return repository.save(accessory);
    }
    public Accessory buyAccessory(Long id, int quantity) {
        Accessory accessory = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accessory not found"));

        if (accessory.getStockQuantity() < quantity) {
            throw new RuntimeException("Not enough stock available");
        }

        
        accessory.setStockQuantity(accessory.getStockQuantity() - quantity);

        
        return repository.save(accessory);
    }
}

 
 

  	


