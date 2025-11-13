package com.caraegis.Accessories.and.Upgrade.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caraegis.Accessories.and.Upgrade.accessoryentity.Accessory;

public interface AccesoryRepository  extends JpaRepository<Accessory,Long>{
	List<Accessory> findByCategory(String category);
    List<Accessory> findByBrand(String brand);
    List<Accessory> findByCompatibleCarModel(String compatibleCarModel);
}


