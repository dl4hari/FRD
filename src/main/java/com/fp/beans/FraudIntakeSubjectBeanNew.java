package com.fp.beans;

import com.fp.model.FraudIntakeValues;
import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Data
public class FraudIntakeSubjectBeanNew implements Serializable {

    List<FraudIntakeValues> subjectValues = Collections.singletonList(new FraudIntakeValues());

}