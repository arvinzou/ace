package com.huacainfo.ace.common.model;

import java.io.Serializable;

import com.huacainfo.ace.common.tools.CommonUtils;

public class PageParamNoChangeSord extends PageParam implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public String getOrderBy() {
		if(!CommonUtils.isBlank(this.orderBy)){
			
			return this.orderBy+" "+this.getSord();
		}
		return null;
	}
}
