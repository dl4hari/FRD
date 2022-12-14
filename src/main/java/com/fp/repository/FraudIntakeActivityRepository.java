package com.fp.repository;

import com.fp.model.FraudIntakeActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudIntakeActivityRepository extends JpaRepository<FraudIntakeActivity, Long> {

}