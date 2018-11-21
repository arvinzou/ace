
package com.huacainfo.ace.portal.vo;

import com.huacainfo.ace.portal.model.Appeal;


public class AppealVo extends Appeal {
	private static final long serialVersionUID = 1L;

	private String nickname;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
}
