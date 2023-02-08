package com.fp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Embeddable
public class IntakeRefValueId implements Serializable {
    @Column(name = "FRAUD_REF_CD")
    public String fraudRefCd;

    @Column(name = "FRAUD_REF_VALUE_ID")
    public String fraudRefValueId;

    public IntakeRefValueId() {
    }

    public IntakeRefValueId(String fraudRefCd, String fraudRefValueId) {
        this.fraudRefCd = fraudRefCd;
        this.fraudRefValueId = fraudRefValueId;
    }
}
