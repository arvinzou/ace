
package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.PaymentPressInfo;


public class PaymentPressInfoVo extends PaymentPressInfo {

	  
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;
	private String departmentName;	
	private String deptName;	
	private String chargingItemName;
	private String legalPersonName;
	private String bussLicenseNo;
	private String legalPersonIdNo;
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getChargingItemName() {
		return chargingItemName;
	}
	public void setChargingItemName(String chargingItemName) {
		this.chargingItemName = chargingItemName;
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
