package com.caragies.repositories;

import com.caragies.entitymodel.Car;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {

    Optional<Car> findByVin(String vin);

    @Query(value = "select * from car where users_id =:id", nativeQuery = true)
    List<Car> findByUsersId(Integer id);
}
