package com.fp.controller;

import com.fp.beans.FraudIntakeMasterRequest;
import com.fp.exception.FRMException;
import com.fp.service.FraudIntakeMasterService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FraudIntakeMasterController {
    @Autowired
    FraudIntakeMasterService fraudIntakeMasterService;

    @ApiOperation(value = "Setting-up to add new ScheduleTask", notes = "Setting-up to add new ScheduleTask")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched new ScheduleTask entity details")})
    @GetMapping(value = "/fraud-intake/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FraudIntakeMasterRequest> addNewFraudIntakeMaster() {
        FraudIntakeMasterRequest FraudIntakeMasterRequest = null;
        try {
            FraudIntakeMasterRequest = fraudIntakeMasterService.addNew();
        } catch (Exception e) {
            e.printStackTrace();
            new FRMException("FraudIntake Load API failed, reason- " + ExceptionUtils.getStackTrace(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(FraudIntakeMasterRequest, HttpStatus.OK);
    }

    @PostMapping(value = "/fraud-intake/save", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FraudIntakeMasterRequest> saveFraudIntakeMaster(
            @RequestPart("fraud-intake-master-json") FraudIntakeMasterRequest fraudIntakeMasterBean,
            @RequestPart("fraud-intake-master-file") MultipartFile multipartFile) {
        FraudIntakeMasterRequest savedFraudIntakeMasterBean = null;

        try {
            savedFraudIntakeMasterBean = fraudIntakeMasterService.save(fraudIntakeMasterBean, multipartFile);
        } catch (Exception e) {
            new FRMException("FraudIntake Save API failed, reason " + ExceptionUtils.getStackTrace(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedFraudIntakeMasterBean, HttpStatus.CREATED);

    }


    @PostMapping(value = "/fraud-intake/update", consumes = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<FraudIntakeMasterRequest> updateFraudIntakeMaster(
            @RequestPart("fraud-intake-master-json") FraudIntakeMasterRequest fraudIntakeMasterBean,
            @RequestPart("fraud-intake-master-file") MultipartFile multipartFile) {
        FraudIntakeMasterRequest savedFraudIntakeMasterBean = null;

        try {
            savedFraudIntakeMasterBean = fraudIntakeMasterService.save(fraudIntakeMasterBean, multipartFile);
        } catch (Exception e) {
            new FRMException("FraudIntake Update API failed, reason " + ExceptionUtils.getStackTrace(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(savedFraudIntakeMasterBean, HttpStatus.OK);


    }

}
