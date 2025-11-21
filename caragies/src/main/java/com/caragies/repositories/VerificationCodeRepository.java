package com.caragies.repositories;

import com.caragies.entitymodel.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {
    Optional<VerificationCode> findTopByEmailAndCodeOrderByCreatedAtDesc(String email, String code);
    void deleteByEmail(String email);
}
