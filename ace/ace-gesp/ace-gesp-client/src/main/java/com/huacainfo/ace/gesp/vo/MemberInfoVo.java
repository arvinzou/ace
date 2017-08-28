package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.MemberInfo;

public class MemberInfoVo extends MemberInfo implements java.io.Serializable {

	/**
	 * serialVersionUID:TODO
	 * 
	 * @since Ver 1.1
	 */

	private static final long serialVersionUID = 1L;
	private String departmentName;

	private String legalPersonName;

	private String bussLicenseNo;
	private String legalPersonIdNo;

	private String memberLevelName;

	private String contactName;

	private String contactMobile;
	
	private Long payNum;
	
	private int leftDates;
	
	private String payStatus;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getLegalPersonName() {
		return legalPersonName;
	}

	public void setLegalPersonName(String legalPersonName) {
		this.legalPersonName = legalPersonName;
	}

	public String getBussLicenseNo() {
		return bussLicenseNo;
	}

	public void setBussLicenseNo(String bussLicenseNo) {
		this.bussLicenseNo = bussLicenseNo;
	}

	public String getLegalPersonIdNo() {
		return legalPersonIdNo;
	}

	public void setLegalPersonIdNo(String legalPersonIdNo) {
		this.legalPersonIdNo = legalPersonIdNo;
	}

	public String getMemberLevelName() {
		return memberLevelName;
	}

	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public Long getPayNum() {
		return payNum;
	}

	public void setPayNum(Long payNum) {
		this.payNum = payNum;
	}

	public int getLeftDates() {
		return leftDates;
	}

	public void setLeftDates(int leftDates) {
		this.leftDates = leftDates;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

}
