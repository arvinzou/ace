package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopCompany;


public class FopCompanyQVo extends FopCompany {
    private static final long serialVersionUID = 1L;

    private boolean isCompany;

    public boolean getIsCompany() {
        return isCompany;
    }

    public void setIsCompany(boolean company) {
        isCompany = company;
    }
}
