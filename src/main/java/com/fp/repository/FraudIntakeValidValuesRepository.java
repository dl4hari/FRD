package com.fp.repository;

import com.fp.model.FraudIntakeValidValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudIntakeValidValuesRepository extends JpaRepository<FraudIntakeValidValues, Long> {
    List<FraudIntakeValidValues> findByPublished(boolean published);

    List<FraudIntakeValidValues> findByTitleContaining(String title);
}