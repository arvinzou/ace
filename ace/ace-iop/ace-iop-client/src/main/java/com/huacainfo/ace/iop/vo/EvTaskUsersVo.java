/**
 * @Title: EvTaskUsersVo.java
 * @Package org.platform.snail.es.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 * @version V1.0
 */

package com.huacainfo.ace.iop.vo;

import com.huacainfo.ace.iop.model.EvTaskUsers;

/**
 * @ClassName: EvTaskUsersVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class EvTaskUsersVo extends EvTaskUsers{
	    private String userName;
	    private String evTaskName;
	    private String gradeName;
	    private String account;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getEvTaskName() {
			return evTaskName;
		}
		public void setEvTaskName(String evTaskName) {
			this.evTaskName = evTaskName;
		}
		public String getGradeName() {
			return gradeName;
		}
		public void setGradeName(String gradeName) {
			this.gradeName = gradeName;
		}
		public String getAccount() {
			return account;
		}
		public void setAccount(String account) {
			this.account = account;
		}
	    
}
