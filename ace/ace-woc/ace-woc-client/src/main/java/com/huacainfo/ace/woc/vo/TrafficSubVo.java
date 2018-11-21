
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.TrafficSub;


public class TrafficSubVo extends TrafficSub {
	private static final long serialVersionUID = 1L;

    private String deviceName;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
