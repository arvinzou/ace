/**
 * @Title: EvTaskVo.java
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

import com.huacainfo.ace.iop.model.EvTask;

/**
 * @ClassName: EvTaskVo
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月24日 下午3:44:43
 *
 */

public class EvTaskVo extends EvTask{
	    private String adminName;
	    private String evObjName;
	    
	    private Integer allUsers;
	    private Integer voteUsers;
	    
	    private String deptName;

		public String getAdminName() {
			return adminName;
		}

		public void setAdminName(String adminName) {
			this.adminName = adminName;
		}

		public String getEvObjName() {
			return evObjName;
		}

		public void setEvObjName(String evObjName) {
			this.evObjName = evObjName;
		}

		public Integer getAllUsers() {
			return allUsers;
		}

		public void setAllUsers(Integer allUsers) {
			this.allUsers = allUsers;
		}

		public Integer getVoteUsers() {
			return voteUsers;
		}

		public void setVoteUsers(Integer voteUsers) {
			this.voteUsers = voteUsers;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}
	    
}
