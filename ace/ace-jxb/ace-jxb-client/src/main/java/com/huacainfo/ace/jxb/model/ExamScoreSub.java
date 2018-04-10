package com.huacainfo.ace.jxb.model;

import java.util.Date;

public class ExamScoreSub implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String examScoreId;

    private String questionId;

    private String optionsId;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getExamScoreId() {
        return examScoreId;
    }

    public void setExamScoreId(String examScoreId) {
        this.examScoreId = examScoreId == null ? null : examScoreId.trim();
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId == null ? null : questionId.trim();
    }

    public String getOptionsId() {
        return optionsId;
    }

    public void setOptionsId(String optionsId) {
        this.optionsId = optionsId == null ? null : optionsId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}