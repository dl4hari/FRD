package com.fp.repository;

import com.fp.model.FraudIntakeLookups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FraudIntakeLookupRepository extends JpaRepository<FraudIntakeLookups, BigDecimal> {
    List<FraudIntakeLookups> findAllByLookupCodeAndLookupType(String lookupCode, String lookupType);
}