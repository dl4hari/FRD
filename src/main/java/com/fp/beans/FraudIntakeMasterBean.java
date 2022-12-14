package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Data
public class FraudIntakeMasterBean implements Serializable {

    public BigDecimal fraudIntakeId;

    public String reportingUserId;

    public String reportDt;

    public String reportType;

    public String fraudTypeCd;

    public String subjectIn;

    public String subjectTypeCd;

    public String subjectTaxId;

    public String customerId;

    public String impactAccountName;

    public String custTypeCd;

    public String acctTakeoverIn;

    public String idTheftRelatedIn;

    public String eventDetailsDesc;

    public String elderExploitIn;

    public String referralReportedBy;

    public String referralDeptNm;

    public String matterIdentifiedCd;

    public String attachment;

    public String auditId;

    public String auditUpdtTs;

    private FraudIntakeSubjectBean fraudIntakeSubjectBean;
    private FraudIntakeActivityBean fraudIntakeActivityBean;


}