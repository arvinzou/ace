package com.huacainfo.ace.taa.vo;

import com.huacainfo.ace.taa.model.RegisterRule;


public class RegisterRuleVo extends RegisterRule {
    private static final long serialVersionUID = 1L;


    /***
     * 导入序号
     */
    private String index;
    /**
     * 部门ID
     */
    private String deptName;
    /**
     * 是否已注册进入系统 0-否，1-是
     */
    private String regStatus;


    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
