
package com.huacainfo.ace.uf.vo;

import com.huacainfo.ace.uf.model.Organization;


public class OrganizationVo extends Organization {
	private static final long serialVersionUID = 1L;
	private String areaName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
