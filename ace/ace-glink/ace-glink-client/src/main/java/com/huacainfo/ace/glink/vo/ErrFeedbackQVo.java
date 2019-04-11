package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.ErrFeedback;


public class ErrFeedbackQVo extends ErrFeedback {
    private static final long serialVersionUID = 1L;

    /**
     * 搜索关键字
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
