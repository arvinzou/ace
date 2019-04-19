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

    private List<SeNodeMonitorDevice> monitorDeviceList;

    public List<SeNodeMonitorDevice> getMonitorDeviceList() {
        return monitorDeviceList;
    }

    public void setMonitorDeviceList(List<SeNodeMonitorDevice> monitorDeviceList) {
        this.monitorDeviceList = monitorDeviceList;
    }
}
