package com.fp.beans;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class FraudIntakeSubjectBeanNew implements Serializable {

    List<FraudIntakeValuesBean> subjectValues = Collections.singletonList(new FraudIntakeValuesBean());

}