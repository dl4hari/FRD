package com.fp.beans;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class FraudIntakeActivityBeanNew implements Serializable {
    List<FraudIntakeValuesBean> activityValues = Collections.singletonList(new FraudIntakeValuesBean());

}