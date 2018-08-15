package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuProject;
import com.huacainfo.ace.cu.model.CuProjectRes;

import java.math.BigDecimal;
import java.util.List;


public class CuProjectVo extends CuProject {
    private static final long serialVersionUID = 1L;

    /**
     * 父类项目名称
     */
    private String parentProjectName;

    /**
     * 距离截止时间生于天数
     */
    private long balanceDays;

    /**
     * 今日已募集总金额
     */
    private BigDecimal todayDonateAmount;
    /**
     * 今日募集捐款次数
     */
    private int todayDonateCount;

    /**
     * 录播资源列表
     */
    private List<CuProjectRes> carousel;

    public List<CuProjectRes> getCarousel() {
        return carousel;
    }

    public void setCarousel(List<CuProjectRes> carousel) {
        this.carousel = carousel;
    }

    public String getParentProjectName() {
        return parentProjectName;
    }

    public void setParentProjectName(String parentProjectName) {
        this.parentProjectName = parentProjectName;
    }


    public BigDecimal getTodayDonateAmount() {
        return todayDonateAmount;
    }

    public void setTodayDonateAmount(BigDecimal todayDonateAmount) {
        this.todayDonateAmount = todayDonateAmount;
    }

    public int getTodayDonateCount() {
        return todayDonateCount;
    }

    public void setTodayDonateCount(int todayDonateCount) {
        this.todayDonateCount = todayDonateCount;
    }

    public long getBalanceDays() {
        return balanceDays;
    }

    public void setBalanceDays(long balanceDays) {
        this.balanceDays = balanceDays;
    }
}
