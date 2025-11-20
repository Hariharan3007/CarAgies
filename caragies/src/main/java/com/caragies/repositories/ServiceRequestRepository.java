package com.caragies.repositories;

import com.caragies.entitydto.ServiceRequestDto;
import com.caragies.entitymodel.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Integer> {

    @Query(value = "select * from service_request where user_id =:id", nativeQuery = true)
    List<ServiceRequest> findByUserId(Integer id);

    @Query(value = "select * from service_request where status ='Requested'", nativeQuery = true)
    List<ServiceRequest> findByRequestedStatus();
}
