
package com.huacainfo.ace.uf.vo;

import com.huacainfo.ace.uf.model.Personage;


public class PersonageVo extends Personage {
	private static final long serialVersionUID = 1L;
	private String deptName;
	private String areaName;
	private String placeOfOriginName;

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

	public String getPlaceOfOriginName() {
		return placeOfOriginName;
	}

	public void setPlaceOfOriginName(String placeOfOriginName) {
		this.placeOfOriginName = placeOfOriginName;
	}
}
