package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Appeal;
import com.huacainfo.ace.portal.vo.AppealVo;
import com.huacainfo.ace.portal.vo.AppealQVo;
/**
 * @author: 陈晓克
 * @version: 2018-05-14
 * @Description:  TODO(诉求模板)
 */
public interface AppealService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(诉求模板分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<AppealVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract PageResult<AppealVo> findAppealList(AppealQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertAppeal
	    * @Description:  TODO(添加诉求模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract MessageResponse insertAppeal(Appeal obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateAppeal
	    * @Description:  TODO(更新诉求模板)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract MessageResponse updateAppeal(Appeal obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectAppealByPrimaryKey
	    * @Description:  TODO(获取诉求模板)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Appeal>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract SingleResult<AppealVo> selectAppealByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteAppealByAppealId
	    * @Description:  TODO(删除诉求模板)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract MessageResponse deleteAppealByAppealId(String id,UserProp userProp) throws Exception;

	
}
