package com.caragies.repositories;

import com.caragies.entitymodel.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepo extends JpaRepository<Car,Integer> {
}
