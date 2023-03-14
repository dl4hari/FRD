package com.fp.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Data
@Embeddable
public class IntakeValueId implements Serializable {
    @Column(name = "FRAUD_INTAKE_ID")
    @NotNull
    private BigDecimal intakeId;

    @Column(name = "ROW_ID")
    @NotNull
    private String rowId;

    @Column(name = "KEY_INDEX_ID")
    @NotNull
    private String keyIndex;

    public IntakeValueId() {
    }

    public IntakeValueId(BigDecimal intakeId, String rowId,  String keyIndex) {
        this.intakeId = intakeId;
        this.keyIndex = keyIndex;
        this.rowId = rowId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntakeValueId that = (IntakeValueId) o;
        return
                Objects.equals(intakeId, that.intakeId)
                        && Objects.equals(keyIndex, that.keyIndex)
        && Objects.equals(rowId, that.rowId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(intakeId, keyIndex, rowId);
    }
}
