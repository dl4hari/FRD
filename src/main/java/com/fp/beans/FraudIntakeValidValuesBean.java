package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@Data
public class FraudIntakeValidValuesBean implements Serializable {

    public String fraudRefCd;
    public String fraudRefValueId;
    public String fraudRefValueCd;
    public String fraudRefValueDesc;
    public String auditUserId;
    public String auditDt;


}