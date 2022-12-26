package com.fp.service;

import com.fp.beans.*;
import com.fp.exception.FRMException;
import com.fp.model.FraudIntakeCodes;
import com.fp.model.FraudIntakeMaster;
import com.fp.model.FraudIntakeValidValues;
import com.fp.repository.FraudIntakeCodesRepository;
import com.fp.repository.FraudIntakeMasterRepository;
import com.fp.repository.FraudIntakeValidValuesRepository;
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
    FraudIntakeCodesRepository codesRepository;

    @Autowired
    private FraudIntakeValidValuesRepository valueRepository;

    @Override
    public FraudIntakeMasterRequest addNew() throws FRMException {

        //preparing master
        FraudIntakeMasterBean fraudIntakeMaster = new FraudIntakeMasterBean();
        fraudIntakeMaster.setFraudIntakeActivityBean(Arrays.asList(new FraudIntakeActivityBean()));
        fraudIntakeMaster.setFraudIntakeSubjectBean(Arrays.asList(new FraudIntakeSubjectBean()));

        //preparing code list
        List<FraudIntakeCodeBean> beanList = new ArrayList<>();
        List<FraudIntakeCodes> codesList = codesRepository.findAll();
        Map<String, List<String>> dropDownMap =  new HashMap<>();
        for (FraudIntakeCodes codes : codesList) {
            FraudIntakeCodeBean codeBean = new FraudIntakeCodeBean();
            codeBean.setFraudRefCd(codes.getFraudRefCd());
            codeBean.setAuditDt(codes.getAuditDt());
            codeBean.setFraudRefType(codes.getFraudRefType());
            codeBean.setFraudRefDesc(codes.getFraudRefDesc());
            codeBean.setAuditUserId(codes.getAuditUserId());
            codeBean.setUiControlOptionCd(codes.getUiControlOptionCd());

            List<FraudIntakeValidValues> _values = valueRepository.fetchValues(codes.getFraudRefCd());
            List<FraudIntakeValidValuesBean> list = new ArrayList<>();
            List<String> values = new ArrayList<>();
            for (FraudIntakeValidValues validValues : _values) {
                FraudIntakeValidValuesBean valuesBean = new FraudIntakeValidValuesBean();
                valuesBean.setFraudRefValueId(validValues.getFraudRefValueId());
                valuesBean.setFraudRefValueDesc(validValues.getFraudRefValueDesc());
                valuesBean.setAuditDt(validValues.getAuditDt());
                valuesBean.setFraudRefCd(validValues.getFraudRefCd());
                valuesBean.setFraudRefValueCd(validValues.getFraudRefValueCd());
                valuesBean.setAuditUserId(validValues.getAuditUserId());
                list.add(valuesBean);
                values.add(validValues.getFraudRefValueCd());
            }
            codeBean.setFraudIntakeValidValuesBean(list);
            beanList.add(codeBean);
            if(!values.isEmpty()) {
                dropDownMap.put(codes.getFraudRefType(), values);
            }
        }

        //response
        FraudIntakeMasterRequest bean = new FraudIntakeMasterRequest();
        bean.setValueMap(dropDownMap);
        bean.setFraudIntakeMaster(fraudIntakeMaster);
      //  bean.setFraudIntakeCodes(beanList);

        return bean;
    }

    @Override
    public FraudIntakeMasterRequest save(FraudIntakeMasterRequest bean, MultipartFile file) throws FRMException {
        FraudIntakeMaster master = prepareMasterFromBean(bean.getFraudIntakeMaster());
        FraudIntakeMaster savedMaster = masterRepository.save(master);
        FraudIntakeMasterBean fraudIntakeMaster = prepareBeanFromMaster(savedMaster);
        bean.setFraudIntakeMaster(fraudIntakeMaster);

        return bean;
    }

    private FraudIntakeMasterBean prepareBeanFromMaster(FraudIntakeMaster savedMaster) {
        FraudIntakeMasterBean bean = new FraudIntakeMasterBean();


        return bean;
    }

    private FraudIntakeMaster prepareMasterFromBean(FraudIntakeMasterBean bean) {
        System.out.println(bean);
        FraudIntakeMaster master = new FraudIntakeMaster();

        //master.setFraudIntakeId(UUID.randomUUID().toString());
        master.setReportingUserId("100");
        master.setReportDt(now());
        master.setReportType(bean.getReportType());
        master.setFraudTypeCd(bean.getFraudTypeCd());
        master.setSubjectIn(bean.getSubjectIn());
        master.setSubjectTypeCd(bean.getSubjectTypeCd());
        master.setSubjectTaxId(bean.getSubjectTaxId());
        master.setCustomerId(bean.getCustomerId());
        master.setImpactAccountName(bean.getAttachment());
        master.setCustTypeCd(bean.getCustTypeCd());
        master.setAcctTakeoverIn(bean.getAcctTakeoverIn());
        master.setIdTheftRelatedIn(bean.getIdTheftRelatedIn());

        master.setEventDetailsDesc(bean.getEventDetailsDesc());
        master.setElderExploitIn(bean.getElderExploitIn());
        master.setReferralReportedBy(bean.getReferralReportedBy());
        master.setReferralDeptNm(bean.getReferralDeptNm());
        master.setMatterIdentifiedCd(bean.getMatterIdentifiedCd());
        master.setAttachment(null);
        master.setAuditId(bean.getAuditId());
        master.setAuditUpdtTs(now());
        System.out.println(master);
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
    public static String now() {
        Calendar cal = Calendar.getInstance();
        String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

}
