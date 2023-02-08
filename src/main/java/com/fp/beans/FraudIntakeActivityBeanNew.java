package com.fp.beans;

import com.fp.model.FraudIntakeValues;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class FraudIntakeActivityBeanNew implements Serializable {
    List<FraudIntakeValues> activityValues = Collections.singletonList(new FraudIntakeValues());

}