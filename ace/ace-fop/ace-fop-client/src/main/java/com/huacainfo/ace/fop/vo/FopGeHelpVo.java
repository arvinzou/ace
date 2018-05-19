package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopGeHelp;


public class FopGeHelpVo extends FopGeHelp {
    private static final long serialVersionUID = 1L;

    private String replied;

    private String companyName;

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
