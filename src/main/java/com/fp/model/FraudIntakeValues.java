package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@Entity
@Table(name = "FRAUD_INTAKE_VALUES")
public class FraudIntakeValues implements Serializable {

    @Column(name = "FRAUD_INTAKE_ID", updatable = false, insertable = false)
    public BigDecimal fraudIntakeId;

    @Column(name = "KEY_INDEX_ID", updatable = false, insertable = false)
    public String keyIndexId;

    @Column(name = "INTAKE_KEY", insertable = false, updatable = false)
    public String intakeKey;

    @Column(name = "INTAKE_KEY_VALUE")
    public String intakeKeyValue;

    @Column(name = "AUDIT_ID")
    public String auditId;

    @Column(name = "AUDIT_UPDT_TS")
    public String auditUpdtTs;

    @EmbeddedId
    private IntakeValueId intakeValueId;


}