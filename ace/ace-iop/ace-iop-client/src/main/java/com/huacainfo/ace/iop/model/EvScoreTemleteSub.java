package com.huacainfo.ace.iop.model;

import java.util.Date;

public class EvScoreTemleteSub implements java.io.Serializable{

    private static final long serialVersionUID = 1L;
    private String id;

    private String scoreTempleId;

    private String skey;

    private String name;

    private Integer sqe;

    private Integer score;

    private String status;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScoreTempleId() {
        return scoreTempleId;
    }

    public void setScoreTempleId(String scoreTempleId) {
        this.scoreTempleId = scoreTempleId == null ? null : scoreTempleId.trim();
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey == null ? null : skey.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSqe() {
        return sqe;
    }

    public void setSqe(Integer sqe) {
        this.sqe = sqe;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
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