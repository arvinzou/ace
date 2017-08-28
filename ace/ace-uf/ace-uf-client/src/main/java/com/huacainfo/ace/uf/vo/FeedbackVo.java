
package com.huacainfo.ace.uf.vo;

import com.huacainfo.ace.uf.model.Feedback;


public class FeedbackVo extends Feedback {
	private static final long serialVersionUID = 1L;

	private String areaName;

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
}
