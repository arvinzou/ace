
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.License;


public class LicenseVo extends License {
    private static final long serialVersionUID = 1L;

    private String personName;

    private String vehicleNo;

    private String departmentName;

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
