package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopProject;

import java.util.List;


public class FopProjectVo extends FopProject {
    private static final long serialVersionUID = 1L;

    private String displayName;

    private String address;

    private String companyProperty;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyProperty() {
        return companyProperty;
    }

    public void setCompanyProperty(String companyProperty) {
        this.companyProperty = companyProperty;
    }

    private List<FopQuestionVo> comments;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<FopQuestionVo> getComments() {
        return comments;
    }

    public void setComments(List<FopQuestionVo> comments) {
        this.comments = comments;
    }
}
