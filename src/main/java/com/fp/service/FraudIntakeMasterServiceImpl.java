package com.fp.service;

import com.fp.beans.FraudIntakeCodeBean;
import com.fp.beans.FraudIntakeMasterBean;
import com.fp.beans.FraudIntakeMasterRequest;
import com.fp.beans.FraudIntakeValidValuesBean;
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

import java.util.ArrayList;
import java.util.List;

@Service
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

        //preparing code list
        List<FraudIntakeCodeBean> beanList = new ArrayList<>();
        List<FraudIntakeCodes> codesList = codesRepository.findAll();

        for (FraudIntakeCodes codes : codesList) {
            FraudIntakeCodeBean codeBean = new FraudIntakeCodeBean();
            codeBean.setFraudRefCd(codes.getFraudRefCd());
            codeBean.setAuditDt(codes.getAuditDt());
            codeBean.setFraudRefType(codes.getFraudRefType());
            codeBean.setFraudRefDesc(codes.getFraudRefDesc());
            codeBean.setAuditUserId(codes.getAuditUserId());
            codeBean.setUiControlOptionCd(codes.getUiControlOptionCd());

            List<FraudIntakeValidValues> _values = valueRepository.findByFraudRefCd(codes.getFraudRefCd());
            List<FraudIntakeValidValuesBean> list = new ArrayList<>();
            for (FraudIntakeValidValues validValues : _values) {
                FraudIntakeValidValuesBean valuesBean = new FraudIntakeValidValuesBean();
                valuesBean.setFraudRefValueId(validValues.getFraudRefValueId());
                valuesBean.setFraudRefValueDesc(validValues.getFraudRefValueDesc());
                valuesBean.setAuditDt(validValues.getAuditDt());
                valuesBean.setFraudRefCd(validValues.getFraudRefCd());
                valuesBean.setFraudRefValueId(validValues.getFraudRefValueId());
                valuesBean.setAuditUserId(validValues.getAuditUserId());
                list.add(valuesBean);
            }
            codeBean.setFraudIntakeValidValuesBean(list);
            beanList.add(codeBean);
        }

        //response
        FraudIntakeMasterRequest bean = new FraudIntakeMasterRequest();
        bean.setFraudIntakeMaster(fraudIntakeMaster);
        bean.setFraudIntakeCodes(beanList);

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

    private FraudIntakeMaster prepareMasterFromBean(FraudIntakeMasterBean fraudIntakeMaster) {
        FraudIntakeMaster master = new FraudIntakeMaster();

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

}
