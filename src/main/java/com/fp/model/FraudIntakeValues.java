package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "FRAUD_INTAKE_VALUES")
public class FraudIntakeValues implements Serializable {


    @Column(name = "INTAKE_KEY_VALUE")
    public String intakeKeyValue;
    @Column(name = "AUDIT_ID")
    public String auditId;
    @Column(name = "AUDIT_UPDT_TS")
    public String auditUpdtTs;

    @EmbeddedId
    private IntakeValueId intakeValueId;


}