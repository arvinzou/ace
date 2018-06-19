package com.huacainfo.ace.cu.vo;

import com.huacainfo.ace.cu.model.CuProject;


public class CuProjectVo extends CuProject {
    private static final long serialVersionUID = 1L;

    /**
     * 距离截止时间生于天数
     */
    private long balanceDays;

    public long getBalanceDays() {
        return balanceDays;
    }

    public void setBalanceDays(long balanceDays) {
        this.balanceDays = balanceDays;
    }
}
