package com.huacainfo.ace.portal.model;

import java.util.Date;

public class DictCategory implements java.io.Serializable {



	private static final long serialVersionUID = 1L;
	private String categoryId;
	private String name;
	private String remark;
	private Date createTime;
	private String syid;

	public String getSyid() {
		return syid;
	}

	public void setSyid(String syid) {
		this.syid = syid;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId == null ? null : categoryId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}