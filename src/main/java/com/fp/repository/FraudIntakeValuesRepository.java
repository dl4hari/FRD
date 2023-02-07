package com.fp.repository;

import com.fp.model.FraudIntakeValues;
import com.fp.model.IntakeValueId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudIntakeValuesRepository extends JpaRepository<FraudIntakeValues, IntakeValueId> {

}