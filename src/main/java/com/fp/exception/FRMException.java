package com.fp.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class FRMException extends RuntimeException {
    private static final long serialVersionUID = 222L;
    private final String errMsg;
    private HttpStatus httpStatus;

    public FRMException(String errMsg) {
        this.errMsg = errMsg;
    }

    public FRMException(String errMsg, HttpStatus httpStatus) {
        super((errMsg == null || errMsg.isEmpty()) ? "Unable to process request" : errMsg);
        this.errMsg = errMsg;
        this.httpStatus = httpStatus;
    }

}
