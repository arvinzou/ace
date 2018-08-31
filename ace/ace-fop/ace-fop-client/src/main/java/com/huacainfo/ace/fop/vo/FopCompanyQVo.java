package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopCompany;


public class FopCompanyQVo extends FopCompany {
    private static final long serialVersionUID = 1L;

    private String isCompany;

    private String companyTypeStr;
    private String[] companyTypeArray;

    public String getCompanyTypeStr() {
        return companyTypeStr;
    }

    public void setCompanyTypeStr(String companyTypeStr) {
        this.companyTypeStr = companyTypeStr;
    }

    public String[] getCompanyTypeArray() {
        return companyTypeArray;
    }

    public void setCompanyTypeArray(String[] companyTypeArray) {
        this.companyTypeArray = companyTypeArray;
    }

    public String getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(String isCompany) {
        this.isCompany = isCompany;
    }
}
