package com.fp.service;

import com.fp.beans.FraudIntakeMasterRequest;
import com.fp.exception.FRMException;
import org.springframework.web.multipart.MultipartFile;

public interface FraudIntakeMasterService {
    FraudIntakeMasterRequest addNew()
            throws FRMException;

    FraudIntakeMasterRequest save(FraudIntakeMasterRequest bean, MultipartFile file)
            throws FRMException;

    FraudIntakeMasterRequest update(FraudIntakeMasterRequest bean, MultipartFile file)
            throws FRMException;


}
