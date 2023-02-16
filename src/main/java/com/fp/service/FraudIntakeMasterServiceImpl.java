package com.fp.service;

import com.fp.beans.*;
import com.fp.exception.FRMException;
import com.fp.model.*;
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
            List<FraudIntakeValuesBean> subjectValues = subject.getSubjectValues();
            int index = 0;
            String indexStr = "";
            for (FraudIntakeValuesBean intakeValue : subjectValues) {
                FraudIntakeValues dbFIN = new FraudIntakeValues();
                indexStr = String.valueOf(index++);

                dbFIN.setIntakeValueId(new IntakeValueId(master.getFraudIntakeId(),
                        indexStr));
                dbFIN.setIntakeKey(intakeValue.getIntakeKey());
                dbFIN.setIntakeKeyValue(intakeValue.getIntakeKeyValue());
                dbFIN.setAuditId(master.getAuditId());
                dbFIN.setAuditUpdtTs(now());
                FraudIntakeValues saved = valueRepo.save(dbFIN);
            }
        }

        //activity
        List<FraudIntakeActivityBeanNew> activityList =
                bean.getFraudIntakeMaster().getFraudIntakeActivityBean();

        for (FraudIntakeActivityBeanNew activity : activityList) {
            int index = 0;
            String indexStr = "";
            for (FraudIntakeValuesBean intakeValue : activity.getActivityValues()) {
                FraudIntakeValues dbFIN = new FraudIntakeValues();
                indexStr = String.valueOf(index++);
                dbFIN.setIntakeValueId(new IntakeValueId(master.getFraudIntakeId(),
                        indexStr));
                dbFIN.setIntakeKey(intakeValue.getIntakeKey());
                dbFIN.setIntakeKeyValue(intakeValue.getIntakeKeyValue());
                dbFIN.setAuditId(master.getAuditId());
                dbFIN.setAuditUpdtTs(now());
                FraudIntakeValues saved = valueRepo.save(dbFIN);
            }
        }
        //impacted acc numbers
        List<String> impactedAccountsNbrList =
                bean.getFraudIntakeMaster().getImpactAccountNumbers();
        int index = 0;
        String indexStr = "";
        FraudIntakeValues dbFIN = null;
        for (String impactedAccountNumberVal : impactedAccountsNbrList) {
            String keyName = "impactedAccountNumber";
            indexStr = String.valueOf(index++);
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(master.getFraudIntakeId(),
                    indexStr));
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountNumberVal);
            dbFIN.setAuditId(master.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            FraudIntakeValues saved = valueRepo.save(dbFIN);
        }

        //impacted acc
        List<ImpactedAccountsBean> impactedAccountsBeanList =
                bean.getFraudIntakeMaster().getImpactedAccounts();
        index = 0;
        indexStr = "";
        dbFIN = null;
        for (ImpactedAccountsBean impactedAccountsBean : impactedAccountsBeanList) {
            String keyName = "impactedAccountNumber";
            indexStr = String.valueOf(index++);
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(master.getFraudIntakeId(),
                    indexStr));
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getImpactedAccountNumber());
            dbFIN.setAuditId(master.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            FraudIntakeValues saved = valueRepo.save(dbFIN);


            keyName = "primarySigner";
            indexStr = String.valueOf(index++);
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(master.getFraudIntakeId(),
                    indexStr));
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getPrimarySigner());
            dbFIN.setAuditId(master.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            saved = valueRepo.save(dbFIN);

            keyName = "primarySignerEmail";
            indexStr = String.valueOf(index++);
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(master.getFraudIntakeId(),
                    indexStr));
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getPrimarySignerEmail());
            dbFIN.setAuditId(master.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            saved = valueRepo.save(dbFIN);

            keyName = "primarySignerPhoneNo";
            indexStr = String.valueOf(index++);
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(master.getFraudIntakeId(),
                    indexStr));
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getPrimarySignerPhoneNo());
            dbFIN.setAuditId(master.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            saved = valueRepo.save(dbFIN);
        }


        //objects or file attachments
        List<FraudIntakeObjectBean> fraudIntakeObjectBeanList = bean.getFraudIntakeMaster().getFraudIntakeObjects();
        for (FraudIntakeObjectBean fraudIntakeObjectBean : fraudIntakeObjectBeanList) {
            FraudIntakeObject object = new FraudIntakeObject();
            object.setFraudIntakeId(master.getFraudIntakeId());
            object.setIntakeFileNm(fraudIntakeObjectBean.getIntakeFileNm());
            object.setAuditId(master.getAuditId());
            object.setAuditUpdtTs(now());
            String content = fraudIntakeObjectBean.getBase64EncodedFileContent();
            content.replaceFirst("data:text/plain;base64,", "");

            byte[] fileContent = Base64.getDecoder().decode(content);
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

        bean.setCustTypeCd(savedMaster.getCustTypeCd());

        bean.setAcctTakeoverIn(savedMaster.getAcctTakeoverIn());

        bean.setIdTheftRelatedIn(savedMaster.getIdTheftRelatedIn());

        bean.setEventDetailsDesc(savedMaster.getEventDetailsDesc());

        bean.setElderExploitIn(savedMaster.getElderExploitIn());

        bean.setReferralReportedBy(savedMaster.getReferralReportedBy());

        bean.setReferralDeptNm(savedMaster.getReferralDeptNm());

        bean.setMatterIdentifiedCd(savedMaster.getMatterIdentifiedCd());

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
        // master.setImpactAccountName(bean.getImpactAccountName());

        return master;
    }


}
