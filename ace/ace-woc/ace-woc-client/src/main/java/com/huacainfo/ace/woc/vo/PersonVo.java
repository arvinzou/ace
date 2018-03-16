
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Person;


public class PersonVo extends Person {
	private String deptName;

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

	private String areaName;
	private static final long serialVersionUID = 1L;
}
