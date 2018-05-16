package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopProject;

import java.util.List;


public class FopProjectVo extends FopProject {
    private static final long serialVersionUID = 1L;

    private String displayName;

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
