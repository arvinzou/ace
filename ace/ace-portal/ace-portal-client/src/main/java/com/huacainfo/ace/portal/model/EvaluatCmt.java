package com.huacainfo.ace.portal.model;

import java.math.BigDecimal;
import java.util.Date;

public class EvaluatCmt implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private String id;

    private String evaluatTplId;

    private String userId;

    private BigDecimal scoreA;

    private BigDecimal scoreB;

    private BigDecimal scoreC;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public BigDecimal getScoreA() {
        return scoreA;
    }

    public void setScoreA(BigDecimal scoreA) {
        this.scoreA = scoreA;
    }

    public BigDecimal getScoreB() {
        return scoreB;
    }

    public void setScoreB(BigDecimal scoreB) {
        this.scoreB = scoreB;
    }

    public BigDecimal getScoreC() {
        return scoreC;
    }

    public void setScoreC(BigDecimal scoreC) {
        this.scoreC = scoreC;
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