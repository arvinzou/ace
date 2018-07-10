package com.huacainfo.ace.fundtown.vo;

import com.huacainfo.ace.fundtown.model.VipDepartment;


public class VipDepartmentVo extends VipDepartment {
    private static final long serialVersionUID = 1L;

    private String userName;

    private String parentDepartmentName;

    private String areaName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getParentDepartmentName() {
        return parentDepartmentName;
    }

    public void setParentDepartmentName(String parentDepartmentName) {
        this.parentDepartmentName = parentDepartmentName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
