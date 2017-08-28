package com.huacainfo.ace.operana.vo;

import com.huacainfo.ace.operana.model.Tpa;


public class TpaQVo extends Tpa {
	private static final long serialVersionUID = 1L;

	private String deptId;
	private String startDate;
	private String endDate;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
