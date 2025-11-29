package com.caragies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caragies.entitymodel.Accessory;

public interface AccessoryRepository  extends JpaRepository<Accessory, Long>{

}
