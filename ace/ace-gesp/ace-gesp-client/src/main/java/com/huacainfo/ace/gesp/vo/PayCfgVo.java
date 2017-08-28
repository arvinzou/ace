
package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.PayCfg;


public class PayCfgVo extends PayCfg implements java.io.Serializable{

	  
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;
	private String departmentName;
	private String memberLevelName;
	private String chargingItemName;
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
	public String getChargingItemName() {
		return chargingItemName;
	}
	public void setChargingItemName(String chargingItemName) {
		this.chargingItemName = chargingItemName;
	}
	
}
