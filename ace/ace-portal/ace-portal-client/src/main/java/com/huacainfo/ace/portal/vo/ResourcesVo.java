/**
 * @Title: ResourcesVo.java
 * @Package com.huacainfo.ace.portal.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月16日 下午3:32:38
 * @version V1.0
 */

package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Resources;

/**
 * @ClassName: ResourcesVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月16日 下午3:32:38
 *
 */

public class ResourcesVo extends Resources {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7414425901259195539L;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
