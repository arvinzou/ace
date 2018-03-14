
package com.huacainfo.ace.woc.vo;

import com.huacainfo.ace.woc.model.Vehicle;


public class VehicleVo extends Vehicle {
	private static final long serialVersionUID = 1L;

	private String personName;

	private String departmentName;

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
