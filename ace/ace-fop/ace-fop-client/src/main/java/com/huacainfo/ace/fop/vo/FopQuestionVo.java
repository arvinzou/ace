package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopQuestion;

import java.util.List;


public class FopQuestionVo extends FopQuestion {
    private static final long serialVersionUID = 1L;


    private List<FopQuestionVo> children;

    public List<FopQuestionVo> getChildren() {
        return children;
    }

    public void setChildren(List<FopQuestionVo> children) {
        this.children = children;
    }
}
