package com.huacainfo.ace.fop.vo;

import com.huacainfo.ace.fop.model.FopQuestion;


public class FopQuestionQVo extends FopQuestion {
    private static final long serialVersionUID = 1L;

    private String replied;

    public String getReplied() {
        return replied;
    }

    public void setReplied(String replied) {
        this.replied = replied;
    }

    private boolean iself;

    public boolean getIself() {
        return iself;
    }

    public void setIself(boolean iself) {
        this.iself = iself;
    }
}
