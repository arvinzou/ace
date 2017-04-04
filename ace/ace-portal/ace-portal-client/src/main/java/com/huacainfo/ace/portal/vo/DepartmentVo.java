package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Department;

public class DepartmentVo extends Department {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2522185206813169632L;

	private String userName;

	private String parentDepartmentName;

	private String areaName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getParentDepartmentName() {
		return parentDepartmentName;
	}

	public void setParentDepartmentName(String parentDepartmentName) {
		this.parentDepartmentName = parentDepartmentName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

}
