
package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.ChargingItem;


public class ChargingItemVo extends ChargingItem {

	  
	    /**    
	     * serialVersionUID:TODO    
	     *    
	     * @since Ver 1.1    
	     */    
	    
	private static final long serialVersionUID = 1L;
	private String departmentName;
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
