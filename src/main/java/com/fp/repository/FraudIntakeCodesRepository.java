package com.fp.repository;

import com.fp.model.FraudIntakeCodes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FraudIntakeCodesRepository extends JpaRepository<FraudIntakeCodes, Long> {
    List<FraudIntakeCodes> findByPublished(boolean published);

    List<FraudIntakeCodes> findByTitleContaining(String title);
}