package com.fp.repository;

import com.fp.model.FraudIntakeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudIntakeMasterRepository extends JpaRepository<FraudIntakeMaster, Long> {

//    @Transactional
//    @Modifying
//    @Query("update FraudIntakeMaster f set f.fraudIntakeId = ?1 where f.fraudIntakeId = ?2")
//    int update(String fraudIntakeId, String fraudIntakeId1);


}