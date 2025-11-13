package com.caraegis.Accessories.and.Upgrade.accessoryentity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Accessory{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long accessoryId;
	    private String name;
	    private String category;
	    private String brand;
	    private String compatibleCarModel;
	    private Double price;
	    private String description;
	    private String vendor;
	    private Integer stockQuantity;

}
