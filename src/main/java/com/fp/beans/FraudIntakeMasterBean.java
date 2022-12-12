package com.fp.beans;

import com.fp.model.FraudIntakeActivity;
import com.fp.model.FraudIntakeMaster;
import com.fp.model.FraudIntakeSubject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@NoArgsConstructor
public class FraudIntakeMasterBean implements Serializable {
    FraudIntakeMaster fraudIntakeMaster;
    private List<DropdownBean> subServiceList;
}