package com.fp.repository;

import com.fp.model.FraudIntakeRefValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface FraudIntakeRefValuesRepository extends JpaRepository<FraudIntakeRefValues, BigDecimal> {

    @Query("select distinct a from FraudIntakeRefValues a where a.fraudRefCd = :fraudRefCd")
    List<FraudIntakeRefValues> fetchValues(String fraudRefCd);
}