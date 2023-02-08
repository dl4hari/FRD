package com.fp.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "FRAUD_INTAKE_REF_VALUES")
public class FraudIntakeRefValues implements Serializable {

    @Column(name = "FRAUD_REF_CD")
    public String fraudRefCd;
    @Column(name = "FRAUD_REF_VALUE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public BigDecimal fraudRefValueId;
    @Column(name = "FRAUD_REF_VALUE_CD")
    public String fraudRefValueCd;
    @Column(name = "FRAUD_REF_VALUE_DESC")
    public String fraudRefValueDesc;
    @Column(name = "AUDIT_USER_ID")
    public String auditUserId;
    @Column(name = "AUDIT_DT")
    public String auditDt;
    @EmbeddedId
    private IntakeRefValueId intakeRefValueId;

}