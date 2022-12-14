package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Data
public class FraudIntakeActivityBean implements Serializable {

    private BigDecimal fraudActivityId;

    private String fraudIntakeId;

    private String productType;

    private String account;

    private String accountTitle;

    private String accountType;

    private String transactionAmount;

    private String fraudTransactionDt;

    private String fundsFrb;

    private String clientInitTransaction;

    private String transactionIdentified;

    private String transactionRecall;


}