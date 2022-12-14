package com.fp.repository;

import com.fp.model.FraudIntakeCodes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudIntakeCodesRepository extends JpaRepository<FraudIntakeCodes, String> {

}