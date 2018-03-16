
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.MonitorSite;


public class MonitorSiteVo extends MonitorSite {
    private static final long serialVersionUID = 1L;

    private String siteName;

    private String areaName;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
}
