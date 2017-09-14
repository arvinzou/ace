
package com.huacainfo.ace.kernel.vo;

import com.huacainfo.ace.kernel.model.Article;


public class ArticleVo extends Article {
	private static final long serialVersionUID = 1L;

	private String authorName;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
