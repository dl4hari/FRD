package com.fp.controller;

import com.fp.model.FraudIntakeMaster;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FraudIntakeMasterController {

    @ApiOperation(value = "Setting-up to add new ScheduleTask", notes = "Setting-up to add new ScheduleTask")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetched new ScheduleTask entity details") })
    @GetMapping(value = "/schtask/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addSchTask() {
        Object schTaskBean = null;
        try {
            //schTaskBean = schTaskService.addNewSchTask(userInfoBean);
        } catch (Exception e) {


        }
        return new ResponseEntity<Object>(schTaskBean, HttpStatus.OK);

    }

    @PostMapping(value = "/master/save", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<FraudIntakeMaster> saveFraudIntakeMasterData(
            @RequestPart("security_rule") FraudIntakeMaster securityEntity, @RequestPart("file") MultipartFile file)
            throws IOException {
        //securityService.saveSecurityService(securityEntity, file)
        return new ResponseEntity<FraudIntakeMaster>(securityEntity,
                HttpStatus.CREATED);

    }

}
