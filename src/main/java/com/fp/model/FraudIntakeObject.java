package com.fp.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "FRAUD_INTAKE_OBJECT")
public class FraudIntakeObject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INTAKE_OBJECT_ID", insertable = false, updatable = false)
    public BigDecimal intakeObjectId;

    @Column(name = "FRAUD_INTAKE_ID")
    public BigDecimal fraudIntakeId;

    @Column(name = "INTAKE_FILE_NM")
    public String intakeFileNm;

    @Column(name = "INTAKE_BIN_OBJECT")
    public byte[] attachment;

    @Column(name = "AUDIT_ID")
    public String auditId;

    @Column(name = "AUDIT_UPDT_TS")
    public String auditUpdtTs;


}