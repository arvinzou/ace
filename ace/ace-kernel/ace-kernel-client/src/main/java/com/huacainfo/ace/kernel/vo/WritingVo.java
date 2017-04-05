
package com.huacainfo.ace.kernel.vo;

import com.huacainfo.ace.kernel.model.Writing;


public class WritingVo extends Writing {
	private static final long serialVersionUID = 1L;

	private  String authorName;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
