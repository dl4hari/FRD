package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Data
@Entity
@Table(name = "FRAUD_INTAKE_VALUES")
public class FraudIntakeValues implements Serializable {

    @Column(name = "FRAUD_INTAKE_ID")
    public String fraudIntakeId;
    @Column(name = "KEY_INDEX_ID")
    public String keyIndexId;
    @Column(name = "INTAKE_KEY")
    public String intakeKey;
    @Column(name = "INTAKE_KEY_VALUE")
    public String intakeKeyValue;
    @EmbeddedId
    private IntakeValueId intakeValueId;


}