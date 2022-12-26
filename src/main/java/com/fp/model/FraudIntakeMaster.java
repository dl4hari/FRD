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
@Table(name = "FRAUD_INTAKE_MASTER")
public class FraudIntakeMaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FRAUD_INTAKE_ID")
    public BigDecimal fraudIntakeId;

    @Column(name = "REPORTING_USER_ID")
    public String reportingUserId;

    @Column(name = "REPORTING_DT")
    public String reportDt;

    @Column(name = "REPORT_TYPE")
    public String reportType;

    @Column(name = "FRAUD_TYPE_CD")
    public String fraudTypeCd;

    @Column(name = "SUBJECT_NM")
    public String subjectIn;

    @Column(name = "SUBJECT_TYPE_CD")
    public String subjectTypeCd;

    @Column(name = "SUBJECT_TAX_ID")
    public String subjectTaxId;

    @Column(name = "CUSTOMER_ID")
    public String customerId;

    @Column(name = "IMPACT_ACCOUNT_NAME")
    public String impactAccountName;

    @Column(name = "CUST_TYPE_CD")
    public String custTypeCd;

    @Column(name = "ACCT_TAKEOVER_IN")
    public String acctTakeoverIn;

    @Column(name = "ID_THEFT_RELATED_IN")
    public String idTheftRelatedIn;

    @Column(name = "EVENT_DETAIL_DESC")
    public String eventDetailsDesc;

    @Column(name = "ELDER_EXPLOIT_IN")
    public String elderExploitIn;

    @Column(name = "REFERRAL_REPORTED_BY")
    public String referralReportedBy;

    @Column(name = "REFERRAL_DEPT_NM")
    public String referralDeptNm;

    @Column(name = "MATTER_IDENTIFIED_CD")
    public String matterIdentifiedCd;

    @Column(name = "ATTACHMENT")
    public byte[]  attachment;

    @Column(name = "AUDIT_ID")
    public String auditId;

    @Column(name = "AUDIT_UPDT_TS")
    public String auditUpdtTs;


}