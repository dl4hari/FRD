package com.fp.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class FraudIntakeLookupRequest implements Serializable {
    public String lookupType;
    private String lookupCode;
}