package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.InformationService;


public class InformationServiceVo extends InformationService {
    private static final long serialVersionUID = 1L;

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
