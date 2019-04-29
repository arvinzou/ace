package com.huacainfo.ace.glink.vo;

import com.huacainfo.ace.glink.model.SeNodeMonitor;
import com.huacainfo.ace.glink.model.SeNodeMonitorDevice;

import java.util.List;

/**
 * @ClassName SeNodeMonitorVo
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/18 20:39
 */
public class SeNodeMonitorVo extends SeNodeMonitor {
    /**
     * 模块数量
     */
    private int deviceCount;
    /**
     * 回路数量
     */
    private int deviceCHCount;


    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }

    public int getDeviceCHCount() {
        return deviceCHCount;
    }

    public void setDeviceCHCount(int deviceCHCount) {
        this.deviceCHCount = deviceCHCount;
    }

    private List<SeNodeMonitorDevice> monitorDeviceList;

    public List<SeNodeMonitorDevice> getMonitorDeviceList() {
        return monitorDeviceList;
    }

    public void setMonitorDeviceList(List<SeNodeMonitorDevice> monitorDeviceList) {
        this.monitorDeviceList = monitorDeviceList;
    }
}
