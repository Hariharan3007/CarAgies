package com.caragies.insurance.repository;

import com.caragies.insurance.entity_model.PartnerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnersRepository extends JpaRepository<PartnerCompany,Integer> {
}
