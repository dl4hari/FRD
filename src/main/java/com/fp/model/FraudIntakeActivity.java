package com.fp.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Data
@Entity
@Table(name = "FRAUD_INTAKE_ACTIVITY")
public class FraudIntakeActivity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRAUD_ACTIVITY_ID")
    public BigDecimal fraudActivityId;

    @Column(name = "FRAUD_INTAKE_ID", insertable = false, updatable = false)
    public String fraudIntakeId;

    @Column(name = "PRODUCT_TYPE")
    public String productType;

    @Column(name = "ACCOUNT")
    public String account;

    @Column(name = "ACCOUNT_TITLE")
    public String accountTitle;

    @Column(name = "ACCOUNT_TYPE")
    public String accountType;

    @Column(name = "TRANSACTION_AMOUNT")
    public String transactionAmount;

    @Column(name = "FRAUD_TRANSACTION_DT")
    public String fraudTransactionDt;

    @Column(name = "FUNDS_FRB")
    public String fundsFrb;

    @Column(name = "CLIENT_INIT_TRANSACTION")
    public String clientInitTransaction;

    @Column(name = "TRANSACTION_IDENTIFIED")
    public String transactionIdentified;

    @Column(name = "TRANSACTION_RECALL")
    public String transactionRecall;

    @Column(name = "TYPE_WIRE_TRANSFER")
    public String typeOfWireTransfer;


}