package com.fp.repository;

import com.fp.model.FraudIntakeRefValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface FraudIntakeRefValuesRepository extends JpaRepository<FraudIntakeRefValues, String> {
    @Modifying
    @Transactional
    @Query(value = "select FRAUD_REF_VALUE_CD from FRAUD_INTAKE_REF_VALUES a where FRAUD_REF_CD = ?1", nativeQuery = true
    )
    List<String> getValidValues(
            String fraudRefCd);
}