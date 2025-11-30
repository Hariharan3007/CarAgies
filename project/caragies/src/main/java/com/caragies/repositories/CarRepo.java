package com.caragies.repositories;

import com.caragies.entitymodel.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car,Integer> {
    Optional<Car> findByVin(String vin);

    @Query(value = "select * from car where users_id =:id", nativeQuery = true)
    List<Car> findByUsersId(Integer id);
}
