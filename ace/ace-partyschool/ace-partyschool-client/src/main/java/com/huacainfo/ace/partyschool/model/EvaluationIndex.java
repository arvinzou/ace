package com.huacainfo.ace.partyschool.model;

import java.io.Serializable;

public class EvaluationIndex implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String evaluatingId;

    private String name;

    private Integer score;
    private Integer index;



    private String introduce;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEvaluatingId() {
        return evaluatingId;
    }

    public void setEvaluatingId(String evaluatingId) {
        this.evaluatingId = evaluatingId == null ? null : evaluatingId.trim();
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }
}