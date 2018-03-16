
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Site;


public class SiteVo extends Site {
	private String roadName;
	private String buildDeptName;
	private String adminDeptName;

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

	private static final long serialVersionUID = 1L;
}
