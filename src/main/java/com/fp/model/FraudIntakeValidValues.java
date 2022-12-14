package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@Data
@Entity
@Table(name = "FRAUD_INTAKE_VALID_VALUES")
public class FraudIntakeValidValues implements Serializable {

    @Id
    @Column(name = "FRAUD_REF_CD", insertable = false, updatable = false)
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

}