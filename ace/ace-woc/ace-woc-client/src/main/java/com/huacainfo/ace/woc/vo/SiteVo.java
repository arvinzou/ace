
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Site;


public class SiteVo extends Site {
    private static final long serialVersionUID = 1L;
    private String roadName;
    private String buildDeptName;
    private String adminDeptName;
    private String areaName;

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public String getBuildDeptName() {
        return buildDeptName;
    }

    public void setBuildDeptName(String buildDeptName) {
        this.buildDeptName = buildDeptName;
    }

    public String getAdminDeptName() {
        return adminDeptName;
    }

    public void setAdminDeptName(String adminDeptName) {
        this.adminDeptName = adminDeptName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
