package com.caragies.insurance.repository;

import com.caragies.insurance.entity_model.CustomerEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

//    Optional<CustomerEntity> findByCustomer_userName(String customerUserName);
}
