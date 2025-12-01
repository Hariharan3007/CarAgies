package com.caragies.repositories;

import com.caragies.entitymodel.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;
@Repository
public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long> {

    // returns single most-recent matching entry (DB-level limit)
    Optional<VerificationCode> findTopByEmailAndCodeOrderByCreatedAtDesc(String email, String code);

    Optional<VerificationCode> findTopByEmailOrderByCreatedAtDesc(String email);

    // delete all codes for email (must be called inside a @Transactional service method)
    @Modifying
    @Query("delete from VerificationCode v where v.email = :email")
    void deleteByEmail(@Param("email") String email);

    // optional helper to inspect duplicates
    @Query("select v from VerificationCode v where v.email = :email order by v.createdAt desc")
    List<VerificationCode> findAllByEmailOrdered(@Param("email") String email);
}
