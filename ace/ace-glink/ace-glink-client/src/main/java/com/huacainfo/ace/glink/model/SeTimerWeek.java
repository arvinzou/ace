package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeTimerWeek extends BaseModel {
    private String id;

    private Integer timerID;

    private Integer w1;

    private Integer w2;

    private Integer w3;

    private Integer w4;

    private Integer w5;

    private Integer w6;

    private Integer w7;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getTimerID() {
        return timerID;
    }

    public void setTimerID(Integer timerID) {
        this.timerID = timerID;
    }

    public Integer getW1() {
        return w1;
    }

    public void setW1(Integer w1) {
        this.w1 = w1;
    }

    public Integer getW2() {
        return w2;
    }

    public void setW2(Integer w2) {
        this.w2 = w2;
    }

    public Integer getW3() {
        return w3;
    }

    public void setW3(Integer w3) {
        this.w3 = w3;
    }

    public Integer getW4() {
        return w4;
    }

    public void setW4(Integer w4) {
        this.w4 = w4;
    }

    public Integer getW5() {
        return w5;
    }

    public void setW5(Integer w5) {
        this.w5 = w5;
    }

    public Integer getW6() {
        return w6;
    }

    public void setW6(Integer w6) {
        this.w6 = w6;
    }

    public Integer getW7() {
        return w7;
    }

    public void setW7(Integer w7) {
        this.w7 = w7;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}