
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.MonitorSiteDetail;


public class MonitorSiteDetailVo extends MonitorSiteDetail {
    private static final long serialVersionUID = 1L;

    private String deviceName;

    private  String deviceNo;

    private  String deviceType;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
