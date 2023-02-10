package com.fp.service;

import com.fp.beans.*;
import com.fp.exception.FRMException;
import com.fp.model.FraudIntakeMaster;
import com.fp.model.FraudIntakeObject;
import com.fp.model.FraudIntakeRef;
import com.fp.model.FraudIntakeValues;
import com.fp.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class FraudIntakeMasterServiceImpl implements FraudIntakeMasterService {

    @Autowired
    FraudIntakeMasterRepository masterRepo;

    @Autowired
    FraudIntakeRefRepository codeRepo;

    @Autowired
    FraudIntakeObjectRepository objRepo;

    @Autowired
    FraudIntakeRefValuesRepository refValuesRepo;

    @Autowired
    FraudIntakeValuesRepository valueRepo;

    public static String now() {
        Calendar cal = Calendar.getInstance();
        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    @Override
    public FraudIntakeMasterRequest addNew() throws FRMException {

        //preparing master
        FraudIntakeMasterBean fraudIntakeMaster = new FraudIntakeMasterBean();

        //code list
        List<FraudIntakeRef> codesList = codeRepo.findAll();
        Map<String, List<String>> dropDownMap = new HashMap<>();
        for (FraudIntakeRef codes : codesList) {
            FraudIntakeCodeBean codeBean = new FraudIntakeCodeBean();
            codeBean.setFraudRefCd(codes.getFraudRefCd());//code
            codeBean.setAuditDt(codes.getAuditDt());
            codeBean.setFraudRefType(codes.getFraudRefType());
            codeBean.setFraudRefDesc(codes.getFraudRefDesc());
            codeBean.setAuditUserId(codes.getAuditUserId());
            codeBean.setUiControlOptionCd(codes.getUiControlOptionCd());

            List<String> values = refValuesRepo.
                    getValidValues(codes.getFraudRefCd());

            if (!values.isEmpty()) {
                dropDownMap.put(codes.getFraudRefType(), values);
            }
        }

        //response
        FraudIntakeMasterRequest bean = new FraudIntakeMasterRequest();
        bean.setValueMap(dropDownMap);
        bean.setFraudIntakeMaster(fraudIntakeMaster);
        // bean.setFraudIntakeCodes(beanList);

        return bean;
    }


    @Override
    public FraudIntakeMasterRequest save(FraudIntakeMasterRequest bean) throws FRMException {

        //master
        FraudIntakeMaster master = prepareMasterFromBean(bean.getFraudIntakeMaster());

        FraudIntakeMaster savedMaster = masterRepo.save(master);

        FraudIntakeMasterBean fraudIntakeMaster = prepareBeanFromMaster(savedMaster);

        //subject
        List<FraudIntakeSubjectBeanNew> subjectList = bean.getFraudIntakeMaster().getFraudIntakeSubjectBean();
        for (FraudIntakeSubjectBeanNew subject : subjectList) {
            List<FraudIntakeValues> subjectValues = subject.getSubjectValues();
            int index = 0;
            for (FraudIntakeValues intakeValue : subjectValues) {
                intakeValue.setFraudIntakeId(savedMaster.getFraudIntakeId());
                intakeValue.setKeyIndexId("" + index++);
                intakeValue.setAuditId(master.getAuditId());
                intakeValue.setAuditUpdtTs(now());
                FraudIntakeValues saved = valueRepo.save(intakeValue);
            }
        }

        //activity
        List<FraudIntakeActivityBeanNew> activityList =
                bean.getFraudIntakeMaster().getFraudIntakeActivityBean();

        for (FraudIntakeActivityBeanNew activity : activityList) {
            int index = 0;
            for (FraudIntakeValues intakeValue : activity.getActivityValues()) {
                intakeValue.setFraudIntakeId(savedMaster.getFraudIntakeId());
                intakeValue.setKeyIndexId("" + index++);
                intakeValue.setAuditId(master.getAuditId());
                intakeValue.setAuditUpdtTs(now());
                FraudIntakeValues saved = valueRepo.save(intakeValue);
            }
        }

        //impacted acc
        List<ImpactedAccountsBean> impactedAccountsBeanList =
                bean.getFraudIntakeMaster().getImpactedAccounts();
        int index = 0;
        for (ImpactedAccountsBean impactedAccountsBean : impactedAccountsBeanList) {

            String impactedAccountNumber =
                    impactedAccountsBean.getImpactedAccountNumber();
            FraudIntakeValues value = new FraudIntakeValues();
            value.setFraudIntakeId(savedMaster.getFraudIntakeId());
            value.setIntakeKey("impactedAccountNumber");
            value.setKeyIndexId("" + index++);
            value.setAuditId(master.getAuditId());
            value.setAuditUpdtTs(now());
            value.setIntakeKeyValue(impactedAccountNumber);
            FraudIntakeValues saved = valueRepo.save(value);


            String primarySigner = impactedAccountsBean.getPrimarySigner();
            value = new FraudIntakeValues();
            value.setFraudIntakeId(savedMaster.getFraudIntakeId());
            value.setIntakeKey("primarySigner");
            value.setKeyIndexId("" + index++);
            value.setIntakeKeyValue(primarySigner);
            value.setAuditId(master.getAuditId());
            value.setAuditUpdtTs(now());
            saved = valueRepo.save(value);

            String primarySignerEmail = impactedAccountsBean.getPrimarySignerEmail();
            value = new FraudIntakeValues();
            value.setFraudIntakeId(savedMaster.getFraudIntakeId());
            value.setIntakeKey("primarySignerEmail");
            value.setKeyIndexId("" + index++);
            value.setIntakeKeyValue(primarySignerEmail);
            value.setAuditId(master.getAuditId());
            value.setAuditUpdtTs(now());
            saved = valueRepo.save(value);

            String primarySignerPhoneNo =
                    impactedAccountsBean.getPrimarySignerPhoneNo();
            value = new FraudIntakeValues();
            value.setFraudIntakeId(savedMaster.getFraudIntakeId());
            value.setIntakeKey("primarySignerPhoneNo");
            value.setKeyIndexId("" + index++);
            value.setIntakeKeyValue(primarySignerPhoneNo);
            value.setAuditId(master.getAuditId());
            value.setAuditUpdtTs(now());
            saved = valueRepo.save(value);
        }


        //objects or file attachments
        List<FraudIntakeObjectBean> fraudIntakeObjectBeanList = bean.getFraudIntakeMaster().getFraudIntakeObjects();
        for (FraudIntakeObjectBean fraudIntakeObjectBean : fraudIntakeObjectBeanList) {
            FraudIntakeObject object = new FraudIntakeObject();
            object.setFraudIntakeId(master.getFraudIntakeId());
            object.setIntakeFileNm(fraudIntakeObjectBean.getIntakeFileNm());
            object.setAuditId(master.getAuditId());
            object.setAuditUpdtTs(now());
            byte[] fileContent = Base64.getDecoder().decode(fraudIntakeObjectBean.getBase64EncodedFileContent());
            object.setAttachment(fileContent);
            objRepo.save(object);
        }
        bean.setFraudIntakeMaster(fraudIntakeMaster);


        return bean;
    }

    private FraudIntakeMasterBean prepareBeanFromMaster(FraudIntakeMaster savedMaster) {
        FraudIntakeMasterBean bean = new FraudIntakeMasterBean();
        bean.setFraudIntakeId(savedMaster.getFraudIntakeId());

        bean.setReportingUserId(savedMaster.getReportingUserId());
        bean.setReportDt(savedMaster.getReportDt());
        bean.setReportType(savedMaster.getReportType());
        bean.setFraudTypeId(savedMaster.getFraudTypeId());
        bean.setSubjectNm(savedMaster.getSubjectIn());

        bean.setSubjectTypeCd(savedMaster.getSubjectTypeCd());

        bean.setSubjectTaxId(savedMaster.getSubjectTaxId());

        bean.setCustomerId(savedMaster.getCustomerId());

        // bean.setImpactAccountName(Arrays.asList(savedMaster.getImpactAccountName().split(",")));

        bean.setCustTypeCd(savedMaster.getCustTypeCd());

        bean.setAcctTakeoverIn(savedMaster.getAcctTakeoverIn());

        bean.setIdTheftRelatedIn(savedMaster.getIdTheftRelatedIn());

        bean.setEventDetailsDesc(savedMaster.getEventDetailsDesc());

        bean.setElderExploitIn(savedMaster.getElderExploitIn());

        bean.setReferralReportedBy(savedMaster.getReferralReportedBy());

        bean.setReferralDeptNm(savedMaster.getReferralDeptNm());

        bean.setMatterIdentifiedCd(savedMaster.getMatterIdentifiedCd());

        // bean.setAttachment(savedMaster.getAuditId());

        bean.setAuditId(savedMaster.getAuditId());

        bean.setAuditUpdtTs(now());


        return bean;
    }

    private FraudIntakeMaster prepareMasterFromBean(FraudIntakeMasterBean bean) {
        System.out.println(bean);
        FraudIntakeMaster master = new FraudIntakeMaster();
        master.setReportingUserId(bean.getReportingUserId() == null ? "100" : bean.getReportingUserId());
        master.setReportDt(now());
        master.setReportType(bean.getReportType());
        master.setFraudTypeId(bean.getFraudTypeId());
        master.setSubjectIn(bean.getSubjectNm());
        master.setSubjectTypeCd(bean.getSubjectTypeCd());
        master.setSubjectTaxId(bean.getSubjectTaxId());
        master.setCustomerId(bean.getCustomerId());

        master.setCustTypeCd(bean.getCustTypeCd());
        master.setAcctTakeoverIn(bean.getAcctTakeoverIn());
        master.setIdTheftRelatedIn(bean.getIdTheftRelatedIn());

        master.setEventDetailsDesc(bean.getEventDetailsDesc());
        master.setElderExploitIn(bean.getElderExploitIn());
        master.setReferralReportedBy(bean.getReferralReportedBy());
        master.setReferralDeptNm(bean.getReferralDeptNm());
        master.setMatterIdentifiedCd(bean.getMatterIdentifiedCd());
        master.setAuditId(bean.getAuditId());
        master.setAuditUpdtTs(now());

        master.setAnonymousCd(bean.getAnonymousCd());
        master.setEmail(bean.getEmail());
        master.setPhoneNo(bean.getPhoneNo());
        master.setIncidentSome(bean.getIncidentSome());
        master.setReportIncident(bean.getReportIncident());
        master.setCyberEvent(bean.getCyberEvent());
        master.setImpactAccountName(bean.getImpactAccountName());

        return master;
    }


}
