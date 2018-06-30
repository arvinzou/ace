package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuDonateList;

import java.math.BigDecimal;


public class CuDonateListVo extends CuDonateList {
    private static final long serialVersionUID = 1L;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 捐助所得积分
     */
    private BigDecimal points;

    /**
     * 微信用户头像
     */
    private String headimgurl;
    /**
     * 微信用户昵称
     */
    private String nickname;

    /**
     * 匿名选项  -- 0-否，1-是
     */
    private String anonymity;

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
