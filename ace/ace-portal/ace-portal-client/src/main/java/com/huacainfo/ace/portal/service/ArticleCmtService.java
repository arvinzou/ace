package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.ArticleCmt;
import com.huacainfo.ace.portal.vo.ArticleCmtVo;
import com.huacainfo.ace.portal.vo.ArticleCmtQVo;
/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public interface ArticleCmtService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ArticleCmtVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract PageResult<ArticleCmtVo> findArticleCmtList(ArticleCmtQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertArticleCmt
	    * @Description:  TODO(添加模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse insertArticleCmt(ArticleCmt obj,UserProp userProp) throws Exception;


    /**
	 *
	    * @Title:selectArticleCmtByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<ArticleCmt>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract SingleResult<ArticleCmtVo> selectArticleCmtByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteArticleCmtByArticleCmtId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse deleteArticleCmtByArticleCmtId(String id,UserProp userProp) throws Exception;

	
}
