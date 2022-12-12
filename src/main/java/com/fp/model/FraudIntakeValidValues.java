package com.fp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "FRAUD_INTAKE_VALID_VALUES")
public class FraudIntakeValidValues implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRAUD_REF_CD")
    public String fraudRefCd;
    @Column(name = "FRAUD_REF_VALUE_ID")
    public String fraudRefValueId;
    @Column(name = "FRAUD_REF_VALUE_CD")
    public String fraudRefValueCd;
    @Column(name = "FRAUD_REF_VALUE_DESC")
    public String fraudRefValueDesc;
    @Column(name = "AUDIT_USER_ID")
    public String auditUserId;
    @Column(name = "AUDIT_DT")
    public String auditDt;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "FRAUD_REF_CD", nullable = false)
    private FraudIntakeCodes fraudIntakeCodes;
}