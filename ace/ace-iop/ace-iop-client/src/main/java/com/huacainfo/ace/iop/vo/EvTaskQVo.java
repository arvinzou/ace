/**
 * @Title: EvTaskVo.java
 * @Package com.huacainfo.ace.iop.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package com.huacainfo.ace.iop.vo;

import com.huacainfo.ace.iop.model.EvTask;

/**
 * @ClassName: EvTaskVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class EvTaskQVo extends EvTask{
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	    
}
