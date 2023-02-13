package com.fp.beans;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FraudIntakeValuesBean implements Serializable {


    public String intakeKeyValue;
    public String auditId;
    public String auditUpdtTs;
    private BigDecimal intakeId;
    private String intakeKey;
    private String keyIndex;


}