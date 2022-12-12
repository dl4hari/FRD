package com.fp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FRAUD_INTAKE_SUBJECT")
public class FraudIntakeSubject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRAUD_SUBJECT_ID")
    public String fraudSubjectId;

    @Column(name = "FRAUD_INTAKE_ID")
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FRAUD_INTAKE_ID", nullable = false)
    private FraudIntakeMaster fraudIntakeMaster;
}