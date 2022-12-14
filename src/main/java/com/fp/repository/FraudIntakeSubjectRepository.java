package com.fp.repository;

import com.fp.model.FraudIntakeSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudIntakeSubjectRepository extends JpaRepository<FraudIntakeSubject, Long> {

}