package com.caragies.repositories;

import com.caragies.entitymodel.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);
   // Optional<Users> findByEmail(String email);
   Optional<Users> findFirstByEmailOrderByIdDesc(String email);
    @Query("SELECT u FROM Users u WHERE u.role = 'user'")
    List<Users> findByRoleUser();
    @Query("SELECT u FROM Users u WHERE u.role = 'vendor'")
    List<Users> findByRoleVendor();
}
