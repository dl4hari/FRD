package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@Entity
@Table(name = "FRAUD_INTAKE_OBJECT")
public class FraudIntakeObject implements Serializable {

    @Id
    @Column(name = "INTAKE_FILE_NM")
    public BigDecimal fraudSubjectId;

    @Column(name = "FRAUD_INTAKE_ID", insertable = false, updatable = false)
    public String fraudIntakeId;

    @Column(name = "INTAKE_BIN_OBJECT")
    public byte[] attachment;

    @Column(name = "AUDIT_ID")
    public String auditId;

    @Column(name = "AUDIT_UPDT_TS")
    public String auditUpdtTs;


}