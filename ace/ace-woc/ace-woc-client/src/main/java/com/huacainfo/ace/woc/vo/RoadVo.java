
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Road;


public class RoadVo extends Road {
    private static final long serialVersionUID = 1L;

    private String areaName;

    private String adminDepName;


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
