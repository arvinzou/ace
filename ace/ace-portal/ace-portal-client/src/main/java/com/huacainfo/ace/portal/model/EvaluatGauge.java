package com.huacainfo.ace.portal.model;

import java.util.Date;

public class EvaluatGauge implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String evaluatTplId;

    private Integer scoreStart;

    private Integer scoreEnd;

    private Date createDate;

    private String content;

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

    public Integer getScoreStart() {
        return scoreStart;
    }

    public void setScoreStart(Integer scoreStart) {
        this.scoreStart = scoreStart;
    }

    public Integer getScoreEnd() {
        return scoreEnd;
    }

    public void setScoreEnd(Integer scoreEnd) {
        this.scoreEnd = scoreEnd;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}