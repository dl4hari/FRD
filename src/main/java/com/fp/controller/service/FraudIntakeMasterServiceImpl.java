package com.fp.controller.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FraudIntakeMasterServiceImpl implements FraudIntakeMasterService {

//    public Object addNewSchTask(Object userInfoBean) throws Exception {
////        SchTaskBean schTaskBean = new SchTaskBean();
////        List<DropdownBean> subServiceList = new ArrayList<DropdownBean>();
////        try {
////            SchTask schTask = new SchTask();
////            SchPreset schPreset = new SchPreset();
////            subServiceList.add(new DropdownBean("DATABASE_ARCHIVING", "Database Archiving"));
////            subServiceList.add(new DropdownBean("FILE_ARCHIVING", "File Archiving"));
////            subServiceList.add(new DropdownBean("SAP_ARCHIVING", "Sap Archiving"));
////            subServiceList.add(new DropdownBean("MIGRATION", "migration"));
////            schTaskBean.setSubServiceList(subServiceList);
////            // schTaskBean.setSubServiceList(utilService.getCustomerLookupsByLookupType("SCH_SUB_SERVICE"));
////            schTaskBean.setTaskTypeList(utilService.getCustomerLookupsByLookupType("SCH_TASK_TYPE"));
////            schTaskBean.setSchTask(schTask);
////            schTaskBean.setSchPresetsList(getSchedulePresets(userInfoBean));
////            schTaskBean.setTimeZoneList(utilService.getCustomerLookupsByLookupType("APPLICATION_DATE_TIMEZONES"));
////            schTaskBean.setSchPreset(schPreset);
////        } catch (Exception e) {
////            log.error("Exception occured while adding new schedule tasks" + e.getMessage());
////            throw e;
////        }
//        return null;
//    }
//
//
//    @Override
//    public Security_Rule_Entity saveSecurityService(Security_Rule_Entity securityEntity, MultipartFile file)
//            throws IOException {
//        LocalDateTime localTime = LocalDateTime.now();
//
//        if (securityEntity.getCreation_date() == null) {
//            securityEntity.setCreation_date(localTime);
//        }
//
//        ((Security_Rule_Entity) securityEntity).setJava_class(file.getBytes());
//        securityEntity.setFileupload(file.getOriginalFilename());
//
//        return securityRepository.save(securityEntity);
//    }

    @Override
    public Object saveSecurityService(Object securityEntity, MultipartFile file) throws IOException {
        return null;
    }
}
