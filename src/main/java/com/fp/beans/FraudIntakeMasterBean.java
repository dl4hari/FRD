package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
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

    public String impactAccountName;

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

    public String anonymousCd;

    public String email;

    public String phoneNo;

    public String incidentSome;

    public String reportIncident;

    public String cyberEvent;

    public List<ImpactedAccountsBean> impactedAccounts =
            Collections.singletonList(new ImpactedAccountsBean());

    public List<FraudIntakeObjectBean> fraudIntakeObjects =
            Collections.singletonList(new FraudIntakeObjectBean());

    private List<FraudIntakeSubjectBeanNew> fraudIntakeSubjectBean = Collections.singletonList(new FraudIntakeSubjectBeanNew());
    private List<FraudIntakeActivityBeanNew> fraudIntakeActivityBean = Collections.singletonList(new FraudIntakeActivityBeanNew());


}