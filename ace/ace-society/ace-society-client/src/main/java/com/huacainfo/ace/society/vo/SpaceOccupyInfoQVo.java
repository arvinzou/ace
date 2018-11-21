package com.huacainfo.ace.society.vo;

import com.huacainfo.ace.society.model.SpaceOccupyInfo;


public class SpaceOccupyInfoQVo extends SpaceOccupyInfo {
    private static final long serialVersionUID = 1L;

    /**
     * 当前系统时间
     */
    private String nowDateTime;

    public String getNowDateTime() {
        return nowDateTime;
    }

    public void setNowDateTime(String nowDateTime) {
        this.nowDateTime = nowDateTime;
    }
}
