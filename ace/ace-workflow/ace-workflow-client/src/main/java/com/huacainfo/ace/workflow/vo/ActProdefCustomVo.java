
package com.huacainfo.ace.workflow.vo;

import com.huacainfo.ace.workflow.model.ActProdefCustom;


public class ActProdefCustomVo extends ActProdefCustom {
	private static final long serialVersionUID = 1L;
	
	private String categoryName;
	private String deptName;
	private String areaName;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	
}
