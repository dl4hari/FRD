package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Data
public class FraudIntakeMasterBean implements Serializable {

    public BigDecimal fraudIntakeId;

    public String reportingUserId;

    public String reportDt;

    public String reportType;

    public String fraudTypeId;

    public String subjectNm;

    public String subjectTypeCd;

    public String subjectTaxId;

    public String customerId;

    //TODO:: verify
    public List<ImpactedAccountsBean> impactAccountName = Arrays.asList(new ImpactedAccountsBean());

    public String custTypeCd;

    public String acctTakeoverIn;

    public String idTheftRelatedIn;

    public String eventDetailsDesc;

    public String elderExploitIn;

    public String referralReportedBy;

    public String referralDeptNm;

    public String matterIdentifiedCd;


    public String auditId;

    public String auditUpdtTs;

    private List<FraudIntakeSubjectBean> fraudIntakeSubjectBean=Arrays.asList(new FraudIntakeSubjectBean());
    private List<FraudIntakeActivityBean> fraudIntakeActivityBean=Arrays.asList(new FraudIntakeActivityBean());
    public List<FraudIntakeObjectBean> fraudIntakeObjects=Arrays.asList(new FraudIntakeObjectBean());



}