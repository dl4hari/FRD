package com.fp.controller.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FraudIntakeMasterService {

    Object saveSecurityService(Object securityEntity, MultipartFile file)
            throws IOException;
}
