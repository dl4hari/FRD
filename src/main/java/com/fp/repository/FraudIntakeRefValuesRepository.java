package com.fp.repository;

import com.fp.model.FraudIntakeRefValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraudIntakeRefValuesRepository extends JpaRepository<FraudIntakeRefValues, String> {

    @Query("select distinct a from FraudIntakeValidValues a where a.fraudRefCd = :fraudRefCd")
    List<FraudIntakeRefValues> fetchValues(String fraudRefCd);
}