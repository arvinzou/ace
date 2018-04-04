
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Traffic;

import java.util.List;


public class TrafficVo extends Traffic {
	private static final long serialVersionUID = 1L;

	private String siteName;

	private String monitorSiteName;

	private List<TrafficSubVo> trafficSubVo;

	public List<TrafficSubVo> getTrafficSubVo() {
		return trafficSubVo;
	}

	public void setTrafficSubVo(List<TrafficSubVo> trafficSubVo) {
		this.trafficSubVo = trafficSubVo;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getMonitorSiteName() {
		return monitorSiteName;
	}

	public void setMonitorSiteName(String monitorSiteName) {
		this.monitorSiteName = monitorSiteName;
	}
}
