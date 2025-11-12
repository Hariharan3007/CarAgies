package com.caragies.insurance.repository;

import com.caragies.insurance.entity_model.VehicleInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleInfoEntity, Integer> {

    @Query(value = "select * from vehicle_info_entity where customer_customer_id=:id", nativeQuery = true)
    List<VehicleInfoEntity> findAllByCustomerId(Integer id);
}
