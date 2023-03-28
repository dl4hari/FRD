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
    FraudIntakeLookupRepository lookupRepository;

    @Autowired
    FraudIntakeValuesRepository valueRepo;

    public static String now() {
        Calendar cal = Calendar.getInstance();
        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }


    @Override
    public List<FraudIntakeLookups> lookups(FraudIntakeLookupRequest lookupRequest)
            throws FRMException {
        return lookupRepository.findAllByLookupCodeAndLookupType(lookupRequest.getLookupCode(), lookupRequest.getLookupType());

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

        //FraudIntakeMasterBean fraudIntakeMaster = prepareBeanFromMaster(savedMaster);

        //subject
        int rowId=0;
        String fraudType="SUBJECT";
        List<FraudIntakeSubjectBeanNew> subjectList = bean.getFraudIntakeMaster().getFraudIntakeSubjectBean();
        for (FraudIntakeSubjectBeanNew subject : subjectList) {
            List<FraudIntakeValuesBean> subjectValues = subject.getSubjectValues();
            int index = 0;
            for (FraudIntakeValuesBean intakeValue : subjectValues) {
                FraudIntakeValues dbFIN = new FraudIntakeValues();
                dbFIN.setIntakeValueId(new IntakeValueId(savedMaster.getFraudIntakeId(),
                        String.valueOf(rowId), String.valueOf(index++)));
                dbFIN.setFraudType(fraudType);
                dbFIN.setIntakeKey(intakeValue.getIntakeKey());
                dbFIN.setIntakeKeyValue(intakeValue.getIntakeKeyValue());
                dbFIN.setAuditId(savedMaster.getAuditId());
                dbFIN.setAuditUpdtTs(now());
                FraudIntakeValues saved = valueRepo.save(dbFIN);
            }
            rowId++;
        }

        //activity
        fraudType="ACTIVITY";
        List<FraudIntakeActivityBeanNew> activityList =
                bean.getFraudIntakeMaster().getFraudIntakeActivityBean();
        rowId=0;
        for (FraudIntakeActivityBeanNew activity : activityList) {
            int index = 0;
            for (FraudIntakeValuesBean intakeValue : activity.getActivityValues()) {
                FraudIntakeValues dbFIN = new FraudIntakeValues();
                dbFIN.setIntakeValueId(new IntakeValueId(savedMaster.getFraudIntakeId(),
                        String.valueOf(rowId), String.valueOf(index++)));
                dbFIN.setFraudType(fraudType);
                dbFIN.setIntakeKey(intakeValue.getIntakeKey());
                dbFIN.setIntakeKeyValue(intakeValue.getIntakeKeyValue());
                dbFIN.setAuditId(savedMaster.getAuditId());
                dbFIN.setAuditUpdtTs(now());
                FraudIntakeValues saved = valueRepo.save(dbFIN);
            }
            rowId++;
        }
        //impacted acc numbers
        fraudType="IMPACTED_ACC_NUMBER";
        List<String> impactedAccountsNbrList =
                bean.getFraudIntakeMaster().getImpactAccountNumbers();
        FraudIntakeValues dbFIN = null;

        //impactedAccountNumber
        rowId = 0;
        int index = 0;
        String keyName = "impactedAccountNumber";
        for (String impactedAccountNumberVal : impactedAccountsNbrList) {

            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(savedMaster.getFraudIntakeId(),
                    String.valueOf(rowId), String.valueOf(index++)));
            dbFIN.setFraudType(fraudType);
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountNumberVal);
            dbFIN.setAuditId(savedMaster.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            FraudIntakeValues saved = valueRepo.save(dbFIN);
        }

        //impacted acc
        rowId = 0;
        index = 0;
        fraudType="IMPACTED_ACCOUNTS";
        List<ImpactedAccountsBean> impactedAccountsBeanList =
                bean.getFraudIntakeMaster().getImpactedAccounts();
        dbFIN = null;
        for (ImpactedAccountsBean impactedAccountsBean : impactedAccountsBeanList) {
            keyName = "impactedAccounts";
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(savedMaster.getFraudIntakeId(),
                    String.valueOf(rowId), String.valueOf(index++)));
            dbFIN.setFraudType(fraudType);
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getImpactedAccountNumber());
            dbFIN.setAuditId(savedMaster.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            FraudIntakeValues saved = valueRepo.save(dbFIN);


            keyName = "primarySigner";
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(savedMaster.getFraudIntakeId(),
                    String.valueOf(rowId), String.valueOf(index++)));
            dbFIN.setFraudType(fraudType);
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getPrimarySigner());
            dbFIN.setAuditId(savedMaster.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            saved = valueRepo.save(dbFIN);

            keyName = "primarySignerEmail";
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(savedMaster.getFraudIntakeId(),
                    String.valueOf(rowId), String.valueOf(index++)));
            dbFIN.setFraudType(fraudType);
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getPrimarySignerEmail());
            dbFIN.setAuditId(savedMaster.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            saved = valueRepo.save(dbFIN);

            keyName = "primarySignerPhoneNo";
            dbFIN = new FraudIntakeValues();
            dbFIN.setIntakeValueId(new IntakeValueId(savedMaster.getFraudIntakeId(),
                    String.valueOf(rowId), String.valueOf(index++)));
            dbFIN.setFraudType(fraudType);
            dbFIN.setIntakeKey(keyName);
            dbFIN.setIntakeKeyValue(impactedAccountsBean.getPrimarySignerPhoneNo());
            dbFIN.setAuditId(savedMaster.getAuditId());
            dbFIN.setAuditUpdtTs(now());
            saved = valueRepo.save(dbFIN);
        }


        //objects or file attachments
        List<FraudIntakeObjectBean> fraudIntakeObjectBeanList = bean.getFraudIntakeMaster().getFraudIntakeObjects();
        for (FraudIntakeObjectBean fraudIntakeObjectBean : fraudIntakeObjectBeanList) {
            FraudIntakeObject object = new FraudIntakeObject();
            object.setFraudIntakeId(savedMaster.getFraudIntakeId());
            object.setIntakeFileNm(fraudIntakeObjectBean.getIntakeFileNm());
            object.setAuditId(savedMaster.getAuditId());
            object.setAuditUpdtTs(now());
            String content = fraudIntakeObjectBean.getBase64EncodedFileContent();
            content.replaceFirst("data:text/plain;base64,", "");

            byte[] fileContent = Base64.getDecoder().decode(content);
            object.setAttachment(fileContent);
            objRepo.save(object);
        }

        //update response and return
        FraudIntakeMasterBean respMaster = bean.getFraudIntakeMaster();
        respMaster.setFraudIntakeId(savedMaster.getFraudIntakeId());
        respMaster.setAuditUpdtTs(savedMaster.getAuditUpdtTs());
        respMaster.setAuditId(savedMaster.getAuditId());

        bean.setFraudIntakeMaster(respMaster);
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
