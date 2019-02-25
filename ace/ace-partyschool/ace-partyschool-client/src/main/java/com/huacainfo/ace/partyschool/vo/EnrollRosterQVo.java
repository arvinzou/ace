package com.huacainfo.ace.partyschool.vo;

import com.huacainfo.ace.partyschool.model.EnrollRoster;


public class EnrollRosterQVo extends EnrollRoster {
    private static final long serialVersionUID = 1L;
    /**
     * 索引关键词
     */
    private String keyword;

    public String getKeyword() {
        return keyword == null ? null : keyword.trim();
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
