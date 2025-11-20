package com.caragies.repositories;

import com.caragies.entitymodel.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {

    Optional<Users> findByUsername(String username);
}
