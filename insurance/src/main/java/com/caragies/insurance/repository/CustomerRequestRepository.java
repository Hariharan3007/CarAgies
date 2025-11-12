package com.caragies.insurance.repository;

import com.caragies.insurance.entity_model.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRequestRepository extends JpaRepository<CustomerRequest, Integer> {

}
