package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.SubjectIdea;


public class SubjectIdeaQVo extends SubjectIdea {
    private static final long serialVersionUID = 1L;

    /**
     * 0-查询自己， 1-查询所有
     */
    private String getAll;

    public String getGetAll() {
        return getAll;
    }

    public void setGetAll(String getAll) {
        this.getAll = getAll;
    }
}
