package com.caragies.insurance.repository;

import com.caragies.insurance.entity_model.CustomerRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<CustomerRequest, Integer> {
    @Query(value = "select * from customer_request where status=:status", nativeQuery = true)
    List<CustomerRequest> findByStatus(boolean status);
}
