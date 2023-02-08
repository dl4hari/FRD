package com.fp.beans;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@Data
public class FraudIntakeObjectBean implements Serializable {
    public BigDecimal intakeFileNm;
    public String fraudIntakeId;
    public byte[] attachment;
}