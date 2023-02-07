package com.fp.repository;

import com.fp.model.FraudIntakeRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudIntakeRefRepository extends JpaRepository<FraudIntakeRef, String> {

}