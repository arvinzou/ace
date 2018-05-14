package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopFinanceProject;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;


public class FopFinanceProjectVo extends FopFinanceProject {
    private static final long serialVersionUID = 1L;

    private String companyName;

    private List<FopQuestionVo> comments;

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

    public List<FopQuestionVo> getComments() {
        return comments;
    }

    public void setComments(List<FopQuestionVo> comments) {
        this.comments = comments;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
