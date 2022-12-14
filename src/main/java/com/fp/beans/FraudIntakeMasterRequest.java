package com.fp.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class FraudIntakeMasterRequest implements Serializable {
    private FraudIntakeMasterBean fraudIntakeMaster;
    private List<FraudIntakeCodeBean> fraudIntakeCodes;
}