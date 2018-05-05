package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Article;
import com.huacainfo.ace.portal.vo.ArticleVo;
import com.huacainfo.ace.portal.vo.ArticleQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public interface ArticleService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract PageResult<ArticleVo> findArticleList(ArticleQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertArticle
	    * @Description:  TODO(添加模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse insertArticle(Article obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateArticle
	    * @Description:  TODO(更新模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse updateArticle(Article obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectArticleByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Article>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract SingleResult<ArticleVo> selectArticleByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteArticleByArticleId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse deleteArticleByArticleId(String id,UserProp userProp) throws Exception;


	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取页面文章列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	public Map<String,Object> getList(Map<String,Object> params) throws Exception;

	
}
