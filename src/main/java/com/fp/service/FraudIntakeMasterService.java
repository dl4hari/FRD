package com.fp.service;

import com.fp.beans.FraudIntakeMasterRequest;
import com.fp.exception.FRMException;

public interface FraudIntakeMasterService {
    FraudIntakeMasterRequest addNew()
            throws FRMException;

    FraudIntakeMasterRequest save(FraudIntakeMasterRequest bean)
            throws FRMException;

}
