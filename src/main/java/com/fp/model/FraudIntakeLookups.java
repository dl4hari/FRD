package com.fp.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "FRAUD_INTAKE_LOOKUPS")
public class FraudIntakeLookups implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LOOKUP_ID")
    public BigDecimal lookupId;

    @Column(name = "LOOKUP_TYPE")
    public String lookupType;

    @Column(name = "LOOKUP_MEANING")
    public String lookupMeaning;

    @Column(name = "LOOKUP_VALUE")
    public String lookupValue;

    @Column(name = "LOOKUP_VALUE1")
    public String lookupValue1;

    @Column(name = "LOOKUP_VALUE2")
    public String lookupValue2;

    @Column(name = "IS_ENABLED")
    public String isEnabled;

    @Column(name = "LOOKUP_CODE")
    private String lookupCode;
}