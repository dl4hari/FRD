package com.fp.service;

import com.fp.beans.FraudIntakeMasterRequest;
import com.fp.exception.FRMException;
import com.fp.model.FraudIntakeLookupRequest;
import com.fp.model.FraudIntakeLookups;

import java.util.List;

public interface FraudIntakeMasterService {
    List<FraudIntakeLookups> lookups(FraudIntakeLookupRequest lookupRequest)
            throws FRMException;
    FraudIntakeMasterRequest addNew()
            throws FRMException;

    FraudIntakeMasterRequest save(FraudIntakeMasterRequest bean)
            throws FRMException;

}
