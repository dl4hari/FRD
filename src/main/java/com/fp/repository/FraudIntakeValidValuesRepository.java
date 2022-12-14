package com.fp.repository;

import com.fp.model.FraudIntakeValidValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FraudIntakeValidValuesRepository extends JpaRepository<FraudIntakeValidValues, String> {

    List<FraudIntakeValidValues> findByFraudRefCd(String fraudRefCd);
}