
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.MonitorSiteDetail;


public class MonitorSiteDetailVo extends MonitorSiteDetail {
    private static final long serialVersionUID = 1L;

    /**
     * 1-添加，2-移除
     */
    private int modifyType;

    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getModifyType() {
        return modifyType;
    }

    public void setModifyType(int modifyType) {
        this.modifyType = modifyType;
    }
}
