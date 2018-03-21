package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvTarget implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String evTargetId;

    private String evTempleteId;

    private String evTargetName;

    private String category;

    private Integer argetScore;

    private String scoreType;

    private String evPoints;

    private String staType;

    private String status;

    private Date createTime;

    public String getEvTargetId() {
        return evTargetId;
    }

    public void setEvTargetId(String evTargetId) {
        this.evTargetId = evTargetId == null ? null : evTargetId.trim();
    }

    public String getEvTempleteId() {
        return evTempleteId;
    }

    public void setEvTempleteId(String evTempleteId) {
        this.evTempleteId = evTempleteId == null ? null : evTempleteId.trim();
    }

    public String getEvTargetName() {
        return evTargetName;
    }

    public void setEvTargetName(String evTargetName) {
        this.evTargetName = evTargetName == null ? null : evTargetName.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public Integer getArgetScore() {
        return argetScore;
    }

    public void setArgetScore(Integer argetScore) {
        this.argetScore = argetScore;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType == null ? null : scoreType.trim();
    }

    public String getEvPoints() {
        return evPoints;
    }

    public void setEvPoints(String evPoints) {
        this.evPoints = evPoints == null ? null : evPoints.trim();
    }

    public String getStaType() {
        return staType;
    }

    public void setStaType(String staType) {
        this.staType = staType == null ? null : staType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}