package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
    public String intakeObjectId;

    @Column(name = "FRAUD_INTAKE_ID")
    public String fraudIntakeId;

    @Column(name = "INTAKE_FILE_NM")
    public BigDecimal intakeFileNm;

    @Column(name = "INTAKE_BIN_OBJECT")
    public byte[] attachment;

    @Column(name = "AUDIT_ID")
    public String auditId;

    @Column(name = "AUDIT_UPDT_TS")
    public String auditUpdtTs;


}