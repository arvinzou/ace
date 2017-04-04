/**
 * @Title: MyAuthenticator.java
 * @Package org.platform.snail.workflow.vo
 * @Description: TODO
 * Copyright: Copyright (c) 2014
 * Company:snail
 * 
 * @author chenxiaoke
 * @date 2014年12月27日 下午5:44:09
 * @version V1.0
 */

package com.huacainfo.ace.workflow.vo;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @ClassName: MyAuthenticator
 * @Description: TODO
 * @author chenxiaoke
 * @date 2014年12月27日 下午5:44:09
 *
 */

public class MyAuthenticator extends Authenticator {

	private String username;
	private String password;

	public MyAuthenticator() {
		System.out.println("myAuthenticator is construct");
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		// TODO Auto-generated method stub
		return new PasswordAuthentication(username, password);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}