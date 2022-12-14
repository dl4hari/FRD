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
@Table(name = "FRAUD_INTAKE_CODES")
public class FraudIntakeCodes implements Serializable {

    @Id
    @Column(name = "FRAUD_REF_CD")
    public String fraudRefCd;

    @Column(name = "FRAUD_REF_TYPE")
    public String fraudRefType;
    @Column(name = "UI_CONTROL_OPTION_CD")
    public String uiControlOptionCd;
    @Column(name = "FRAUD_REF_DESC")
    public String fraudRefDesc;
    @Column(name = "AUDIT_USER_ID")
    public String auditUserId;

    @Column(name = "AUDIT_DT")
    public String auditDt;

}