package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Role;

public class RoleVo extends Role{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1178030057531548789L;
	private String operater;

	public String getOperater() {
		return operater;
	}

	public void setOperater(String operater) {
		this.operater = operater;
	}

	@Override
	public String toString() {
		return "RoleVo [operater=" + operater + ", getOperater()="
				+ getOperater() + ", getRoleId()=" + getRoleId()
				+ ", getRoleName()=" + getRoleName() + ", getCreateTime()="
				+ getCreateTime() + ", getCreateUserId()=" + getCreateUserId()
				+ ", getRemark()=" + getRemark() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	

}
