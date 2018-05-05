package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.ArticleCategory;
import com.huacainfo.ace.portal.vo.ArticleCategoryVo;
import com.huacainfo.ace.portal.vo.ArticleCategoryQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public interface ArticleCategoryService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleCategoryVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract PageResult<ArticleCategoryVo> findArticleCategoryList(ArticleCategoryQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertArticleCategory
	    * @Description:  TODO(添加模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse insertArticleCategory(ArticleCategory obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateArticleCategory
	    * @Description:  TODO(更新模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse updateArticleCategory(ArticleCategory obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectArticleCategoryByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ArticleCategory>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract SingleResult<ArticleCategoryVo> selectArticleCategoryByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteArticleCategoryByArticleCategoryId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse deleteArticleCategoryByArticleCategoryId(String id,UserProp userProp) throws Exception;

	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取页面文章分类列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	public Map<String,Object> getList(Map<String,Object> params) throws Exception;

	
}
