package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Tpl;
import com.huacainfo.ace.portal.vo.TplVo;
import com.huacainfo.ace.portal.vo.TplQVo;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-05-04
 * @Description:  TODO(模板)
 */
public interface TplService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TplVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract PageResult<TplVo> findTplList(TplQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertTpl
	    * @Description:  TODO(添加模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse insertTpl(Tpl obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateTpl
	    * @Description:  TODO(更新模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse updateTpl(Tpl obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectTplByPrimaryKey
	    * @Description:  TODO(获取模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Tpl>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract SingleResult<TplVo> selectTplByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteTplByTplId
	    * @Description:  TODO(删除模板)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-04
	 */
	public abstract MessageResponse deleteTplByTplId(String id,UserProp userProp) throws Exception;


	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取模板列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-04
	 */
	public  Map<String,Object> getList() throws Exception;

	
}
