package com.caragies.insurance.repository;

import com.caragies.insurance.entity_model.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    @Query(value = "select * from admin_entity where user_name =:userName and password=:password", nativeQuery = true)
    public Optional<AdminEntity> findByUserNameAndPassword(String userName, String password);

}
