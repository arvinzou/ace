
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Road;


public class RoadVo extends Road {
    private static final long serialVersionUID = 1L;

    private String areaName;

    private String adminDepName;

    private String roadStatusName;

    public String getRoadStatusName() {
        switch (getRoadStatus()) {
            case "1":
                return "在运营";
            case "2":
                return "养护中";
            case "3":
                return "已废弃";
            default:
                return "";
        }
    }

    public void setRoadStatusName(String roadStatusName) {
        this.roadStatusName = roadStatusName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAdminDepName() {
        return adminDepName;
    }

    public void setAdminDepName(String adminDepName) {
        this.adminDepName = adminDepName;
    }
}
