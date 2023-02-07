package com.fp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class IntakeValueId implements Serializable {
    @Column(name = "FRAUD_INTAKE_ID")
    private String intakeId;
    @Column(name = "INTAKE_KEY")
    private String intakeKey;
    @Column(name = "INTAKE_INDEX")
    private String keyIndex;


    public IntakeValueId() {
    }

    public IntakeValueId(String intakeId, String intakeKey, String keyIndex) {
        this.intakeId = intakeId;
        this.intakeKey = intakeKey;
        this.keyIndex = keyIndex;
    }
}
