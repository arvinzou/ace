package com.huacainfo.ace.glink.model;

import com.huacainfo.ace.common.model.BaseModel;

import java.util.Date;

public class LeLampStatus extends BaseModel {
    private String id;

    private String checkDate;

    private String checkYear;

    private String checkMonth;

    private String checkDay;

    private Integer lampCount;

    private Integer brokenLampCount;

    private String remark;

    private String status;

    private Date createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate == null ? null : checkDate.trim();
    }

    public String getCheckYear() {
        return checkYear;
    }

    public void setCheckYear(String checkYear) {
        this.checkYear = checkYear == null ? null : checkYear.trim();
    }

    public String getCheckMonth() {
        return checkMonth;
    }

    public void setCheckMonth(String checkMonth) {
        this.checkMonth = checkMonth == null ? null : checkMonth.trim();
    }

    public String getCheckDay() {
        return checkDay;
    }

    public void setCheckDay(String checkDay) {
        this.checkDay = checkDay == null ? null : checkDay.trim();
    }

    public Integer getLampCount() {
        return lampCount;
    }

    public void setLampCount(Integer lampCount) {
        this.lampCount = lampCount;
    }

    public Integer getBrokenLampCount() {
        return brokenLampCount;
    }

    public void setBrokenLampCount(Integer brokenLampCount) {
        this.brokenLampCount = brokenLampCount;
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
