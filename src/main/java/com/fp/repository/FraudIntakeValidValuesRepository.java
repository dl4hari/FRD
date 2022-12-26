package com.fp.repository;

import com.fp.model.FraudIntakeValidValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraudIntakeValidValuesRepository extends JpaRepository<FraudIntakeValidValues, String> {

    @Query("select distinct a from FraudIntakeValidValues a where a.fraudRefCd = :fraudRefCd")
    List<FraudIntakeValidValues> fetchValues(String fraudRefCd);
}