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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin//(origins = "http://localhost:4200")
@RestController
public class FraudIntakeMasterController {
    @Autowired
    FraudIntakeMasterService fraudIntakeMasterService;

    @ApiOperation(value = "Setting-up to edit form", notes = "Setting-up to edit form")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched new ScheduleTask entity details")})
    @GetMapping(value = "/fraud-intake/edit", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FraudIntakeMasterRequest> editFraudIntakeMaster(@RequestBody FraudIntakeMasterRequest request) {
        FraudIntakeMasterRequest fraudIntakeMasterRequest = null;
        try {
            fraudIntakeMasterRequest = fraudIntakeMasterService.edit(request);
        } catch (Exception e) {
            e.printStackTrace();
            new FRMException("FraudIntake Load API failed, reason- " + ExceptionUtils.getStackTrace(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(fraudIntakeMasterRequest, HttpStatus.OK);
    }


    @ApiOperation(value = "Setting-up to add new form", notes = "Setting-up to add new form")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched new form entity details")})
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

    @PostMapping(value = "/fraud-intake/save2", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FraudIntakeMasterRequest> saveFraudIntakeMaster2(
            @RequestBody FraudIntakeMasterRequest fraudIntakeMasterBean) {
        FraudIntakeMasterRequest savedFraudIntakeMasterBean = null;

        try {
            savedFraudIntakeMasterBean = fraudIntakeMasterService.save(fraudIntakeMasterBean, null);

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
