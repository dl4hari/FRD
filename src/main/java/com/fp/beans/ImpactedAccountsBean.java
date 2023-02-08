package com.fp.beans;

import lombok.Data;

import java.io.Serializable;
@Data
public class ImpactedAccountsBean implements Serializable {
   String impactedAccountNumber;
   String primarySigner;
   String primarySignerEmail;
   String primarySignerPhoneNo;
}
