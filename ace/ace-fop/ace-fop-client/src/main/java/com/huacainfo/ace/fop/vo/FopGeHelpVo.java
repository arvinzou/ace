package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopGeHelp;


public class FopGeHelpVo extends FopGeHelp {
    private static final long serialVersionUID = 1L;

    private String replied;

    private String companyName;

    private String companyProperty;

    private String address;

    public String getCompanyProperty() {
        return companyProperty;
    }

    public void setCompanyProperty(String companyProperty) {
        this.companyProperty = companyProperty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getReplied() {
        return replied;
    }

    public void setReplied(String replied) {
        this.replied = replied;
    }
}
