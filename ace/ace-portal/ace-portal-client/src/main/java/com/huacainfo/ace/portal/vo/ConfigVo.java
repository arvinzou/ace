/**
 * @Title: ConfigVo.java
 * @Package com.huacainfo.ace.portal.vo
 *
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Config;

/**
 * @ClassName: ConfigVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class ConfigVo extends Config {
	private static final long serialVersionUID = 1L;
	/**
	 * 分类名称
	 */
	private String categoryName;
	
	private String departmentName;

	/**
	 * 分类名称
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * 分类名称
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
