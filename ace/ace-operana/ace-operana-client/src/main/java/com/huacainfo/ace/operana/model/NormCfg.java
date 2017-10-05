package com.huacainfo.ace.operana.model;

import java.math.BigDecimal;
import java.util.Date;

public class NormCfg implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private String id;

	private String normId;

	private String year;

	private java.math.BigDecimal indexValue;
	private java.math.BigDecimal value1;
	private java.math.BigDecimal value2;
	private java.math.BigDecimal value3;
	private java.math.BigDecimal value4;

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

	public BigDecimal getValue1() {
		return value1;
	}

	public void setValue1(BigDecimal value1) {
		this.value1 = value1;
	}

	public BigDecimal getValue2() {
		return value2;
	}

	public void setValue2(BigDecimal value2) {
		this.value2 = value2;
	}

	public BigDecimal getValue3() {
		return value3;
	}

	public void setValue3(BigDecimal value3) {
		this.value3 = value3;
	}

	public BigDecimal getValue4() {
		return value4;
	}

	public void setValue4(BigDecimal value4) {
		this.value4 = value4;
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