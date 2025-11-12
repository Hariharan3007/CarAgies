package com.caragies.insurance.repository;

import com.caragies.insurance.entity_model.ServicerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicerRepository extends JpaRepository<ServicerEntity, Integer> {

}
