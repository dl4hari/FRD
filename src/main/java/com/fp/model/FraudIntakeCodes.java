package com.fp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "FRAUD_INTAKE_CODES")
public class FraudIntakeCodes implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "fraudIntakeCodes", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<FraudIntakeValidValues> fraudIntakeValidValues;
}