
package com.huacainfo.ace.gesp.vo;

import com.huacainfo.ace.gesp.model.MemberLevel;


public class MemberLevelVo extends MemberLevel {

	  
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
