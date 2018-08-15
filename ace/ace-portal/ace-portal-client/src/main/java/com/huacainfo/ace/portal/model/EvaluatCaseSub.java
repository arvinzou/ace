package com.huacainfo.ace.portal.model;

import java.util.Date;

public class EvaluatCaseSub implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String evaluatCaseId;

    private String key;

    private String name;

    private String cntImg;

    private Date createDate;

    private Integer sort;

    private String isAnswer;

    private Integer optionScore;

    public Integer getOptionScore() {
        return optionScore;
    }

    public void setOptionScore(Integer optionScore) {
        this.optionScore = optionScore;
    }

    public String getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(String isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEvaluatCaseId() {
        return evaluatCaseId;
    }

    public void setEvaluatCaseId(String evaluatCaseId) {
        this.evaluatCaseId = evaluatCaseId == null ? null : evaluatCaseId.trim();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key == null ? null : key.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCntImg() {
        return cntImg;
    }

    public void setCntImg(String cntImg) {
        this.cntImg = cntImg == null ? null : cntImg.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}