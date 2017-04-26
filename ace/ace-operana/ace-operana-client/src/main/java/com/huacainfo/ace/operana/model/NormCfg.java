package com.huacainfo.ace.operana.model;

import java.util.Date;

public class NormCfg implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;

	private String normId;

	private String year;

	private java.math.BigDecimal indexValue;

	private String status;

	private String createUserId;

	private String createUserName;

	private Date createDate;

	private String lastModifyUserId;

	private String lastModifyUserName;

	private Date lastModifyDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getNormId() {
		return normId;
	}

	public void setNormId(String normId) {
		this.normId = normId == null ? null : normId.trim();
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year == null ? null : year.trim();
	}

	public java.math.BigDecimal getIndexValue() {
		return indexValue;
	}

	public void setIndexValue(java.math.BigDecimal indexValue) {
		this.indexValue = indexValue;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId == null ? null : createUserId.trim();
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName == null ? null : createUserName.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getLastModifyUserId() {
		return lastModifyUserId;
	}

	public void setLastModifyUserId(String lastModifyUserId) {
		this.lastModifyUserId = lastModifyUserId == null ? null : lastModifyUserId.trim();
	}

	public String getLastModifyUserName() {
		return lastModifyUserName;
	}

	public void setLastModifyUserName(String lastModifyUserName) {
		this.lastModifyUserName = lastModifyUserName == null ? null : lastModifyUserName.trim();
	}

	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}
}