package com.huacainfo.ace.operana.vo;

import com.huacainfo.ace.operana.model.NormCfg;


public class NormCfgQVo extends NormCfg {
	private static final long serialVersionUID = 1L;

	private String name;

	private String category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
