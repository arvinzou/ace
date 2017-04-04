package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Users;

public class UsersVo extends Users{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8521496413609014156L;

	private String departmentName;
	
	private String areaName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}	
}
