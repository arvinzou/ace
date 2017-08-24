/**
 * @Title: ConfigVo.java
 * @Package com.huacainfo.ace.portal.vo
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

public class ConfigQVo extends Config {

	/**
	 * serialVersionUID:TODO
	 * 
	 * @since Ver 1.1
	 */

	private static final long serialVersionUID = 1L;
	private String admin;

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

}
