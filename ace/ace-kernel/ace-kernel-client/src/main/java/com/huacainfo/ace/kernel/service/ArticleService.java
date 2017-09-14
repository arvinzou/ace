package com.huacainfo.ace.kernel.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.kernel.model.Article;
import com.huacainfo.ace.kernel.vo.ArticleVo;
import com.huacainfo.ace.kernel.vo.ArticleQVo;
public interface ArticleService {
	
	public abstract PageResult<ArticleVo> findArticleList(ArticleQVo condition, int start, int limit, String orderBy) throws Exception;
	public abstract MessageResponse insertArticle(Article obj,UserProp userProp) throws Exception;
	public abstract MessageResponse updateArticle(Article obj,UserProp userProp) throws Exception;
	public abstract SingleResult<Article> selectArticleByPrimaryKey(String id) throws Exception;
	public abstract MessageResponse deleteArticleByArticleId(String id,UserProp userProp) throws Exception;

	
}
