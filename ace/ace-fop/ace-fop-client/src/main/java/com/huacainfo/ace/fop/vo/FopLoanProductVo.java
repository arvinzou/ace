
package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopLoanProduct;

import java.util.List;


public class FopLoanProductVo extends FopLoanProduct {
    private static final long serialVersionUID = 1L;

    /**
     * 企业名称
     */
    private String companyName;

    /**
     * 企业性质
     */
    private String companyProperty;

    /**
     * 地址
     */
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

    private List<FopQuestionVo> comments;

    public List<FopQuestionVo> getComments() {
        return comments;
    }

    public void setComments(List<FopQuestionVo> comments) {
        this.comments = comments;
    }
}
