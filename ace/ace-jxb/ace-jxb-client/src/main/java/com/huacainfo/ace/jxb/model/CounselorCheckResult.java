package com.huacainfo.ace.jxb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CounselorCheckResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String counselorId;

    private String checkYear;

    private String checkMonth;

    private String checkDay;

    private String checkQuarter;

    private Integer counselorNum;

    private BigDecimal turnover;

    private String remark;

    private String status;

    private Date createDate;

    private Date updateDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(String counselorId) {
        this.counselorId = counselorId == null ? null : counselorId.trim();
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

    public String getCheckQuarter() {
        return checkQuarter;
    }

    public void setCheckQuarter(String checkQuarter) {
        this.checkQuarter = checkQuarter == null ? null : checkQuarter.trim();
    }

    public Integer getCounselorNum() {
        return counselorNum;
    }

    public void setCounselorNum(Integer counselorNum) {
        this.counselorNum = counselorNum;
    }

    public BigDecimal getTurnover() {
        return turnover;
    }

    public void setTurnover(BigDecimal turnover) {
        this.turnover = turnover;
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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}