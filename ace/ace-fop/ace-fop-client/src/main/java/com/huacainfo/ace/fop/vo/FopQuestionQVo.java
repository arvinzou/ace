package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopQuestion;


public class FopQuestionQVo extends FopQuestion {
    private static final long serialVersionUID = 1L;

    private String replied;

    /**
     * 0-否，1-true
     */
    private String isLawer;
    private boolean iself;

    public String getIsLawer() {
        return isLawer;
    }

    public void setIsLawer(String isLawer) {
        this.isLawer = isLawer;
    }

    public String getReplied() {
        return replied;
    }

    public void setReplied(String replied) {
        this.replied = replied;
    }

    public boolean getIself() {
        return iself;
    }

    public void setIself(boolean iself) {
        this.iself = iself;
    }
}
