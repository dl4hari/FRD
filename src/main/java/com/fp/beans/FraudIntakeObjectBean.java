package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@Data
public class FraudIntakeObjectBean implements Serializable {
    public String intakeFileNm;
    public BigDecimal fraudIntakeId;
    public String base64EncodedFileContent;

}