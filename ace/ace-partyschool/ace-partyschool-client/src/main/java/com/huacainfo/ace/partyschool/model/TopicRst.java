package com.huacainfo.ace.partyschool.model;

import java.math.BigDecimal;
import java.util.Date;

public class TopicRst {
    private String id;

    private String content;

    private String type;

    private  BigDecimal youScore;

    private String answer;

    private String status;

    private BigDecimal tscore;

    private String testRstId;

    private  Integer tindex;

    public Integer getTindex() {
        return tindex;
    }

    public void setTindex(Integer tindex) {
        this.tindex = tindex;
    }

    public String getTestRstId() {
        return testRstId;
    }

    public void setTestRstId(String testRstId) {
        this.testRstId = testRstId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTscore() {
        return tscore;
    }

    public void setTscore(BigDecimal tscore) {
        this.tscore = tscore;
    }

    public BigDecimal getYouScore() {
        return youScore;
    }

    public void setYouScore(BigDecimal youScore) {
        this.youScore = youScore;
    }
}