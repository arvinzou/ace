package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class SeTimerMonth extends BaseModel {
    private String id;

    private Integer timerID;

    private Integer m1;

    private Integer m2;

    private Integer m3;

    private Integer m4;

    private Integer m5;

    private Integer m6;

    private Integer m7;

    private Integer m8;

    private Integer m9;

    private Integer m10;

    private Integer m11;

    private Integer m12;

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

    public Integer getM1() {
        return m1;
    }

    public void setM1(Integer m1) {
        this.m1 = m1;
    }

    public Integer getM2() {
        return m2;
    }

    public void setM2(Integer m2) {
        this.m2 = m2;
    }

    public Integer getM3() {
        return m3;
    }

    public void setM3(Integer m3) {
        this.m3 = m3;
    }

    public Integer getM4() {
        return m4;
    }

    public void setM4(Integer m4) {
        this.m4 = m4;
    }

    public Integer getM5() {
        return m5;
    }

    public void setM5(Integer m5) {
        this.m5 = m5;
    }

    public Integer getM6() {
        return m6;
    }

    public void setM6(Integer m6) {
        this.m6 = m6;
    }

    public Integer getM7() {
        return m7;
    }

    public void setM7(Integer m7) {
        this.m7 = m7;
    }

    public Integer getM8() {
        return m8;
    }

    public void setM8(Integer m8) {
        this.m8 = m8;
    }

    public Integer getM9() {
        return m9;
    }

    public void setM9(Integer m9) {
        this.m9 = m9;
    }

    public Integer getM10() {
        return m10;
    }

    public void setM10(Integer m10) {
        this.m10 = m10;
    }

    public Integer getM11() {
        return m11;
    }

    public void setM11(Integer m11) {
        this.m11 = m11;
    }

    public Integer getM12() {
        return m12;
    }

    public void setM12(Integer m12) {
        this.m12 = m12;
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