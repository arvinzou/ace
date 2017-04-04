package com.huacainfo.ace.common.security.spring;

public class BasicAuthorities {
	private String url;
	private String token;

	public BasicAuthorities(String url, String token) {
		super();
		this.url = url;
		this.token = token;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
