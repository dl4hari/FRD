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
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class FraudIntakeMasterServiceImpl implements FraudIntakeMasterService {

    @Autowired
    FraudIntakeMasterRepository masterRepository;

    @Autowired
    FraudIntakeRefRepository codesRepository;

    @Autowired
    private FraudIntakeRefValuesRepository valueRepository;
    @Autowired
    private FraudIntakeObjectRepository fraudIntakeSubjectRepository;
    @Autowired
    private FraudIntakeValuesRepository fraudIntakeActivityRepository;

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
      //  fraudIntakeMaster.setFraudIntakeActivityBean(Collections.singletonList(new FraudIntakeActivityBeanNew()));
        //fraudIntakeMaster.setFraudIntakeSubjectBean(Collections.singletonList(new FraudIntakeSubjectBeanNew()));

        //code list
        List<FraudIntakeCodeBean> beanList = new ArrayList<>();
        List<FraudIntakeRef> codesList = codesRepository.findAll();
        Map<String, List<String>> dropDownMap = new HashMap<>();
        for (FraudIntakeRef codes : codesList) {
            FraudIntakeCodeBean codeBean = new FraudIntakeCodeBean();
            codeBean.setFraudRefCd(codes.getFraudRefCd());//code
            codeBean.setAuditDt(codes.getAuditDt());
            codeBean.setFraudRefType(codes.getFraudRefType());
            codeBean.setFraudRefDesc(codes.getFraudRefDesc());
            codeBean.setAuditUserId(codes.getAuditUserId());
            codeBean.setUiControlOptionCd(codes.getUiControlOptionCd());

            List<String> values = valueRepository.
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
    public FraudIntakeMasterRequest save(FraudIntakeMasterRequest bean, MultipartFile file) throws FRMException {

        //master
        FraudIntakeMaster master = prepareMasterFromBean(bean.getFraudIntakeMaster());

        FraudIntakeMaster savedMaster = masterRepository.save(master);

        FraudIntakeMasterBean fraudIntakeMaster = prepareBeanFromMaster(savedMaster);

        //subject
        List<FraudIntakeValues> subjectList = null;//prepareSubjectFromBean(bean.getFraudIntakeMaster().getFraudIntakeSubjectBean());
        List<FraudIntakeSubjectBean> subjectListSaved = new ArrayList<>();
        for (FraudIntakeValues subject : subjectList) {
//            FraudIntakeValues savedSubject = fraudIntakeSubjectRepository.save(subject);
//            subjectListSaved.add(prepareSubjectBeanFromSaved(savedSubject));
        }

        //activity
        List<FraudIntakeObject> activityList = null;//prepareActivityFromBean(bean.getFraudIntakeMaster().getFraudIntakeActivityBean());
        List<FraudIntakeActivityBean> activityListSaved = new ArrayList<>();
        for (FraudIntakeObject activity : activityList) {
//            FraudIntakeObject savedActivity = fraudIntakeActivityRepository.save(activity);
//            activityListSaved.add(prepareActivityBeanFromSaved(savedActivity));
        }


        // fraudIntakeMaster.setFraudIntakeSubjectBean(subjectListSaved);

        // fraudIntakeMaster.setFraudIntakeActivityBean(activityListSaved);

        bean.setFraudIntakeMaster(fraudIntakeMaster);

        return bean;
    }

    private FraudIntakeActivityBean prepareActivityBeanFromSaved(FraudIntakeObject savedActivity) {
        return null;
    }

    private List<FraudIntakeObject> prepareActivityFromBean(List<FraudIntakeActivityBean> fraudIntakeActivityBean) {
        return null;
    }

    private FraudIntakeSubjectBean prepareSubjectBeanFromSaved(FraudIntakeValues savedSubject) {
        FraudIntakeSubjectBean bean = new FraudIntakeSubjectBean();

        return bean;
    }

    private List<FraudIntakeValues> prepareSubjectFromBean(List<FraudIntakeSubjectBean> beanList) {

        List<FraudIntakeValues> toSave = new ArrayList<>();
        for (FraudIntakeSubjectBean bean : beanList) {
            toSave.add(convertSubjectToSave(bean));
        }

        return toSave;
    }

    private FraudIntakeValues convertSubjectToSave(FraudIntakeSubjectBean bean) {
        FraudIntakeValues subject = new FraudIntakeValues();
        subject.setFraudIntakeId(bean.getFraudIntakeId());
        subject.setIntakeKey("subjectType");
        subject.setIntakeKeyValue(bean.getSubjectNm());

        // subject.setFraudSubjectId(bean.setFraudSubjectId());
        return null;

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

        bean.setAuditUpdtTs(savedMaster.getAuditUpdtTs());


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
        //public String impactAccountName;
        // master.setImpactAccountName(String.join(",", bean.getImpactAccountName()));

        master.setCustTypeCd(bean.getCustTypeCd());
        master.setAcctTakeoverIn(bean.getAcctTakeoverIn());
        master.setIdTheftRelatedIn(bean.getIdTheftRelatedIn());

        master.setEventDetailsDesc(bean.getEventDetailsDesc());
        master.setElderExploitIn(bean.getElderExploitIn());
        master.setReferralReportedBy(bean.getReferralReportedBy());
        master.setReferralDeptNm(bean.getReferralDeptNm());
        master.setMatterIdentifiedCd(bean.getMatterIdentifiedCd());
        // master.setAttachment(bean.getAttachment().getBytes());
        master.setAuditId(bean.getAuditId());
        master.setAuditUpdtTs(now());

        master.setAnonymousCd(bean.getAnonymousCd());
        master.setEmail(bean.getEmail());
        master.setPhoneNo(bean.getPhoneNo());
        master.setIncidentSome(bean.getIncidentSome());
        master.setReportIncident(bean.getReportIncident());
        master.setCyberEvent(bean.getCyberEvent());

        return master;
    }

    @Override
    public FraudIntakeMasterRequest update(FraudIntakeMasterRequest bean, MultipartFile file) throws FRMException {
        FraudIntakeMaster master = prepareMasterFromBean(bean.getFraudIntakeMaster());
        FraudIntakeMaster savedMaster = masterRepository.save(master);
        FraudIntakeMasterBean fraudIntakeMaster = prepareBeanFromMaster(savedMaster);
        bean.setFraudIntakeMaster(fraudIntakeMaster);

        return bean;
    }

    @Override
    public FraudIntakeMasterRequest edit(FraudIntakeMasterRequest request) {
        return null;
    }

}
