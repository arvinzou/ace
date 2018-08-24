package com.huacainfo.ace.jxb.vo;

import com.huacainfo.ace.jxb.model.Counselor;

import java.math.BigDecimal;


public class CounselorVo extends Counselor {
    private static final long serialVersionUID = 1L;

    /**
     * 工作室名称（推荐人名称）
     */
    private String studioName;
    /**
     * 注册资格审核结果 0-待审核 1-审核通过 2-审核拒绝
     */
    private String regAuditRst;

    /**
     * 咨询师产品信息
     */
    //咨询最低价格
    private BigDecimal minCPAmount;
    //咨询最高价格
    private BigDecimal maxCPAmount;
    //咨询价格区间
    private String consultPriceScope;

    private String nickname;
    private String headimgurl;
    /**
     * 同 Consult.java.onlineStatus
     */
    private String onlineStatus;
    /**
     * 同 Consult.java.status
     */
    private String consultState;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(String onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getConsultState() {
        return consultState;
    }

    public void setConsultState(String consultState) {
        this.consultState = consultState;
    }

    public String getConsultPriceScope() {
        String start = null == getMinCPAmount() ? "0" : getMinCPAmount().intValue() + "";
        String end = null == getMaxCPAmount() ? "0" : getMaxCPAmount().intValue() + "";
        return start + "-" + end;

//        return consultPriceScope;
    }

    public void setConsultPriceScope(String consultPriceScope) {
        this.consultPriceScope = consultPriceScope;
    }

    public BigDecimal getMinCPAmount() {
        return minCPAmount;
    }

    public void setMinCPAmount(BigDecimal minCPAmount) {
        this.minCPAmount = minCPAmount;
    }

    public BigDecimal getMaxCPAmount() {
        return maxCPAmount;
    }

    public void setMaxCPAmount(BigDecimal maxCPAmount) {
        this.maxCPAmount = maxCPAmount;
    }

    public String getStudioName() {
        return studioName;
    }

    public void setStudioName(String studioName) {
        this.studioName = studioName;
    }

    public String getRegAuditRst() {
        return regAuditRst;
    }

    public void setRegAuditRst(String regAuditRst) {
        this.regAuditRst = regAuditRst;
    }
}
