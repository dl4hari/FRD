package com.fp.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class FraudIntakeMasterRequest implements Serializable {
    private FraudIntakeMasterBean fraudIntakeMaster;
    private Map<String, List<String>> valueMap;
}