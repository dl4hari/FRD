package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@Data
public class FraudIntakeCodeBean implements Serializable {
    private String fraudRefCd;
    private String fraudRefType;
    private String uiControlOptionCd;
    private String fraudRefDesc;
    private String auditUserId;
    private String auditDt;
    private List<FraudIntakeValidValuesBean> fraudIntakeValidValuesBean;
}