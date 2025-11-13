package com.caraegis.Accessories.and.Upgrade.AccessoryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.caraegis.Accessories.and.Upgrade.Service.AccessoryService;
import com.caraegis.Accessories.and.Upgrade.accessoryentity.Accessory;

@RestController
@RequestMapping("/api/accessories")
public class AccessoryController {
	 @Autowired
	    private AccessoryService accessoryService;
	@GetMapping("/getAccessory")
	public List<Accessory>getAll(){
		return accessoryService.getAllAccessories();
		
	}
	 @GetMapping("/category/{category}")
	    public List<Accessory> getByCategory(@PathVariable String category) {
	        return accessoryService.getByCategory(category);
	    }

	    @PostMapping("/addAccessory")
	    public Accessory addAccessory(@RequestBody Accessory accessory) {
	        return accessoryService.addAccessory(accessory);
	    }

	    @DeleteMapping("/{id}")
	    public String deleteAccessory(@PathVariable Long id) {
	        accessoryService.deleteAccessory(id);
	        return "Accessory deleted successfully";
	    }
	    @PutMapping("/{id}/update")
	    public Accessory updatePriceAndStock(
	            @PathVariable Long id,
	            @RequestParam(required = false) Double price,
	            @RequestParam(required = false) Integer stockQuantity) {
	        return accessoryService.updatePriceAndStock(id, price, stockQuantity);
	    }

	    @PostMapping("/{id}/buy")
	    public ResponseEntity<Accessory> buyAccessory(
	            @PathVariable Long id,
	            @RequestParam int quantity) {

	        Accessory updatedAccessory = accessoryService.buyAccessory(id, quantity);
	        return ResponseEntity.ok(updatedAccessory);

}
}