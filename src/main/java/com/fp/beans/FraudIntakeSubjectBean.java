package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class FraudIntakeSubjectBean implements Serializable {

    // private BigDecimal fraudSubjectId;

    private String fraudIntakeId;

    private String subjectType;

    private String networkId;

    private String subjectNm;

    private String officeNm;

    private String subjectDep;

    private String region;

    private String managerNm;


    private String hrPartnerNm;

    private String empInitial;

    // private String transactionRecall;


}