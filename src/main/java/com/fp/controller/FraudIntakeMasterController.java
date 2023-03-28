package com.fp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fp.beans.FraudIntakeMasterRequest;
import com.fp.exception.FRMException;
import com.fp.model.FraudIntakeLookupRequest;
import com.fp.model.FraudIntakeLookups;
import com.fp.service.FraudIntakeMasterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin//(origins = "http://localhost:4200")
@RestController
public class FraudIntakeMasterController {
    @Autowired
    FraudIntakeMasterService fraudIntakeMasterService;


    @ApiOperation(value = "Setting-up to add new form", notes = "Setting-up to add new form")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully fetched new form entity details")})
    @PostMapping(value = "/fraud-intake/lookups", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<FraudIntakeLookups>> lookups(@RequestBody FraudIntakeLookupRequest request) {
        return new ResponseEntity<>(fraudIntakeMasterService.lookups(request), HttpStatus.OK);
    }

    @ApiOperation(value = "Setting-up to add new form", notes = "Setting-up to add new form")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successfully fetched new form entity details")})
    @GetMapping(value = "/fraud-intake/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FraudIntakeMasterRequest> addNewFraudIntakeMaster() {
        FraudIntakeMasterRequest FraudIntakeMasterRequest;
        try {
            FraudIntakeMasterRequest = fraudIntakeMasterService.addNew();
        } catch (Exception e) {
            throw new FRMException("FraudIntake Load API failed, reason- " + ExceptionUtils.getStackTrace(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(FraudIntakeMasterRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/fraud-intake/save", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FraudIntakeMasterRequest> saveFraudIntakeMaster(@RequestBody FraudIntakeMasterRequest fraudIntakeMasterBean) {
        FraudIntakeMasterRequest savedFraudIntakeMasterBean;

        try {
            String s = new ObjectMapper().writeValueAsString(new FraudIntakeMasterRequest());

            savedFraudIntakeMasterBean = fraudIntakeMasterService.save(fraudIntakeMasterBean);

        } catch (Exception e) {
            throw new FRMException("FraudIntake Save API failed, reason " + ExceptionUtils.getStackTrace(e), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedFraudIntakeMasterBean, HttpStatus.CREATED);

    }


}
