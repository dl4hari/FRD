package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@Entity
@Table(name = "FRAUD_INTAKE_SUBJECT")
public class FraudIntakeSubject implements Serializable {

    @Id
    @Column(name = "FRAUD_SUBJECT_ID")
    public BigDecimal fraudSubjectId;

    @Column(name = "FRAUD_INTAKE_ID", insertable = false, updatable = false)
    public String fraudIntakeId;

    @Column(name = "SUBJECT_TYPE")
    public String subjectType;

    @Column(name = "NETWORK_ID")
    public String networkId;

    @Column(name = "SUBJECT_NM")
    public String subjectNm;

    @Column(name = "OFFICE_NM")
    public String officeNm;

    @Column(name = "SUBJECT_DEP")
    public String subjectDep;

    @Column(name = "REGION")
    public String region;

    @Column(name = "MANGER_NM")
    public String managerNm;


    @Column(name = "HR_PARTNER_NM")
    public String hrPartnerNm;

    @Column(name = "EMP_INITIAL")
    public String empInitial;

    @Column(name = "TRANSACTION_RECALL")
    public String transactionRecall;


}