package com.caragies.service_interface;

import com.caragies.entitydto.CarDto;
import com.caragies.entitymodel.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Integer> {

    Optional<Car> findByVin(String vin);

    @Query(value = "select * from car where users_id =:id", nativeQuery = true)
    List<Car> findByUsersId(Integer id);
}
