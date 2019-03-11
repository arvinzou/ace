package com.huacainfo.ace.policeschool.vo;

import com.huacainfo.ace.policeschool.model.EnrollRoster;


public class EnrollRosterVo extends EnrollRoster {
    private static final long serialVersionUID = 1L;
    /**
     * 注册状态 -- 1-待注册 2-已注册
     */
    private String regStatus;

    private String clsName;

    private String areaCodeName;

    public String getClsName() {
        return clsName;
    }

    public void setClsName(String clsName) {
        this.clsName = clsName;
    }

    public String getAreaCodeName() {
        return areaCodeName;
    }

    public void setAreaCodeName(String areaCodeName) {
        this.areaCodeName = areaCodeName;
    }

    public String getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(String regStatus) {
        this.regStatus = regStatus;
    }
}
