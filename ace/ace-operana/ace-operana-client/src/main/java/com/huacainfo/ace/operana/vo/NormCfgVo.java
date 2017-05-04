
package com.huacainfo.ace.operana.vo;

import com.huacainfo.ace.operana.model.NormCfg;


public class NormCfgVo extends NormCfg {
	private static final long serialVersionUID = 1L;

	private String name;

	private String category;

	private String groups;

	private String deptId;

	private String calType;

	private String pcode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getCalType() {
		return calType;
	}

	public void setCalType(String calType) {
		this.calType = calType;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
}
