package com.huacainfo.ace.fop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class
FopFinanceProject implements Serializable {
    private static final long serialVersionUID = 8457105589824024743L;
    private String id;

    private String financeTitle;

    private String financeCode;

    private String areaCode;

    private String companyId;

    private Date releaseDate;

    private BigDecimal financeAmount;

    private String financeYear;

    private BigDecimal yearYield;

    private Long clicks;
    private Long likes;
    private String remark;
    /**
     * 审核状态 1- 审核中，2-审核通过，3-审核不通过
     */
    private String status;
    private String createUserId;
    private String createUserName;
    private Date createDate;
    private String lastModifyUserId;
    private String lastModifyUserName;
    private Date lastModifyDate;
    private String financeContent;

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getFinanceTitle() {
        return financeTitle;
    }

    public void setFinanceTitle(String financeTitle) {
        this.financeTitle = financeTitle == null ? null : financeTitle.trim();
    }

    public String getFinanceCode() {
        return financeCode;
    }

    public void setFinanceCode(String financeCode) {
        this.financeCode = financeCode == null ? null : financeCode.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    public BigDecimal getFinanceAmount() {
        return financeAmount;
    }

    public void setFinanceAmount(BigDecimal financeAmount) {
        this.financeAmount = financeAmount;
    }

    public String getFinanceYear() {
        return financeYear;
    }

    public void setFinanceYear(String financeYear) {
        this.financeYear = financeYear == null ? null : financeYear.trim();
    }

    public BigDecimal getYearYield() {
        return yearYield;
    }

    public void setYearYield(BigDecimal yearYield) {
        this.yearYield = yearYield;
    }


    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
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

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifyUserId() {
        return lastModifyUserId;
    }

    public void setLastModifyUserId(String lastModifyUserId) {
        this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
    }

    public String getLastModifyUserName() {
        return lastModifyUserName;
    }

    public void setLastModifyUserName(String lastModifyUserName) {
        this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
    }

    public Date getLastModifyDate() {
        return lastModifyDate;
    }

    public void setLastModifyDate(Date lastModifyDate) {
        this.lastModifyDate = lastModifyDate;
    }

    public String getFinanceContent() {
        return financeContent;
    }

    public void setFinanceContent(String financeContent) {
        this.financeContent = financeContent == null ? null : financeContent.trim();
    }
}