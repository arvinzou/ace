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
