package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuUser;

import java.math.BigDecimal;


public class CuUserVo extends CuUser {
    private static final long serialVersionUID = 1L;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 微信头像
     */
    private String headimgurl;

    /**
     * 累计捐款金额
     */
    private BigDecimal accDonateAmount;
    /**
     * 累计捐款次数
     */
    private int accDonateCount;

    public BigDecimal getAccDonateAmount() {
        return accDonateAmount;
    }

    public void setAccDonateAmount(BigDecimal accDonateAmount) {
        this.accDonateAmount = accDonateAmount;
    }

    public int getAccDonateCount() {
        return accDonateCount;
    }

    public void setAccDonateCount(int accDonateCount) {
        this.accDonateCount = accDonateCount;
    }

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
}
