package com.fp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class IntakeRefValueId implements Serializable {
    @Column(name = "FRAUD_INTAKE_ID")
    private String intakeId;
    @Column(name = "INTAKE_KEY")
    private String intakeKey;
    @Column(name = "KEY_INDEX_ID")
    private String keyIndex;

    public IntakeRefValueId() {
    }

    public IntakeRefValueId(String intakeId, String intakeKey, String keyIndex) {
        this.intakeId = intakeId;
        this.intakeKey = intakeKey;
        this.keyIndex = keyIndex;
    }
}
