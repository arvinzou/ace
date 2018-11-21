package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuProjectApply;
import com.huacainfo.ace.cu.model.CuProjectApplyRes;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


public class CuProjectApplyVo extends CuProjectApply {
    private static final long serialVersionUID = 1L;


    private String projectName;
    private String coverUrl;
    private BigDecimal collectAmount;
    private Date endDate;

    /**
     * 距离截止时间生于天数
     */
    private long balanceDays;
    /**
     * 资料附件列表
     */
    private List<CuProjectApplyRes> resList;

    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 微信头像
     */
    private String headimgurl;

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

    public long getBalanceDays() {
        return balanceDays;
    }

    public void setBalanceDays(long balanceDays) {
        this.balanceDays = balanceDays;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public BigDecimal getCollectAmount() {
        return collectAmount;
    }

    public void setCollectAmount(BigDecimal collectAmount) {
        this.collectAmount = collectAmount;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<CuProjectApplyRes> getResList() {
        return resList;
    }

    public void setResList(List<CuProjectApplyRes> resList) {
        this.resList = resList;
    }
}
