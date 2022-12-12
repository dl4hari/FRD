package com.fp.utils;

import com.fp.beans.DropdownBean;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UtilsService {

    public List<DropdownBean> getCustomerLookupsByLookupType(String lookupType) {
        List<DropdownBean> databaseList = new ArrayList<>();
        try {
            log.info("trying to get CustomerLookup details by lookupType, "+lookupType);
            List<Object[]> custLookupList = null;//customerLookupRepo.loadCustomerLookupsByCustIdAndLookupType(customerId ,lookupType);
            DropdownBean dropdownBean = null;
            for (Object[] data : custLookupList) {
                dropdownBean = new DropdownBean();
               // dropdownBean.setId(data[0].toString());
                //dropdownBean.setName(data[1].toString());
                log.debug("Customer Lookup Data ---> " + data[0] + "   " + data[1]);
                databaseList.add(dropdownBean);
            }
        }
        catch (Exception e) {

        }
        return databaseList;
    }

}
