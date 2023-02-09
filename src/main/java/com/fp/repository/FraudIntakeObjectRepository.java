package com.fp.repository;

import com.fp.model.FraudIntakeObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface FraudIntakeObjectRepository extends JpaRepository<FraudIntakeObject, BigDecimal> {

}