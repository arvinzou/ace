package com.huacainfo.ace.gesp.vo;

import java.util.Date;

import com.huacainfo.ace.gesp.model.Department;

public class DepartmentVo extends Department implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String areaName;
	private String categoryName;
	private String parementDepartmentName;
	private Date joinDate;

	private Date endDate;

	private String memberLevel;
	
	private String memberLevelName;
	
	private String memberNo;

	private String memberId;
	
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getParementDepartmentName() {
		return parementDepartmentName;
	}

	public void setParementDepartmentName(String parementDepartmentName) {
		this.parementDepartmentName = parementDepartmentName;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}

	public String getMemberLevelName() {
		return memberLevelName;
	}

	public void setMemberName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

}
