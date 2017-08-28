
package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.MemberPayInfo;


public class MemberPayInfoVo extends MemberPayInfo implements java.io.Serializable{

	  
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;
	
	private String departmentName;	
	private String memberLevelName;

	private String legalPersonName;
	private String bussLicenseNo;
	private String legalPersonIdNo;
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getMemberLevelName() {
		return memberLevelName;
	}
	public void setMemberLevelName(String memberLevelName) {
		this.memberLevelName = memberLevelName;
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
	
	
	
}
