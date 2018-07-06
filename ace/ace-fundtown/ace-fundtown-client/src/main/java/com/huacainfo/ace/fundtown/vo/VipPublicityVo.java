package com.huacainfo.ace.fundtown.vo;

import com.huacainfo.ace.fundtown.model.VipPublicity;


public class VipPublicityVo extends VipPublicity {
    private static final long serialVersionUID = 1L;

    /**
     * 所属机构ID
     */
    private String pid;
    /**
     * 所属机构名称
     */
    private String pName;
    /**
     * 机构/基金名称
     */
    private String departmentName;
    /**
     * 法定代表/执行事务合伙人
     */
    private String legalPersonName;
    /**
     * 注册资本/实缴 出资额（万元）
     */
    private String regCapital;
    /**
     * 注册地址
     */
    private String regAddr;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName;
    }

    public String getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(String regCapital) {
        this.regCapital = regCapital;
    }

    public String getRegAddr() {
        return regAddr;
    }

    public void setRegAddr(String regAddr) {
        this.regAddr = regAddr;
    }
}
