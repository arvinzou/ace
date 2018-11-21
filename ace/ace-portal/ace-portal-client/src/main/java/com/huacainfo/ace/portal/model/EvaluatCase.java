package com.huacainfo.ace.portal.model;

import java.util.Date;

public class EvaluatCase implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String evaluatTplId;

    private String title;

    private String type;

    private String cntImg;

    private Integer score;

    private String solution;

    private Integer sort;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEvaluatTplId() {
        return evaluatTplId;
    }

    public void setEvaluatTplId(String evaluatTplId) {
        this.evaluatTplId = evaluatTplId == null ? null : evaluatTplId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCntImg() {
        return cntImg;
    }

    public void setCntImg(String cntImg) {
        this.cntImg = cntImg == null ? null : cntImg.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}