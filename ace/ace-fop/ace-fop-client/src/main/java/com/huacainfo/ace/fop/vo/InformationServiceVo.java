package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopQuestion;
import com.huacainfo.ace.fop.model.InformationService;

import java.util.List;


public class InformationServiceVo extends InformationService {
    private static final long serialVersionUID = 1L;

    private String companyName;

    private List<FopQuestionVo> comments;

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
