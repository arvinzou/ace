package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.AppealCase;
import com.huacainfo.ace.portal.model.AppealCaseFile;
import com.huacainfo.ace.portal.vo.AppealCaseVo;
import com.huacainfo.ace.portal.vo.AppealCaseQVo;
import java.util.List;
import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2018-05-14
 * @Description:  TODO(诉求)
 */
public interface AppealCaseService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(诉求分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<AppealCaseVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract PageResult<AppealCaseVo> findAppealCaseList(AppealCaseQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertAppealCase
	    * @Description:  TODO(添加诉求)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract MessageResponse insertAppealCase(AppealCase obj, List<AppealCaseFile> list) throws Exception;

    /**
	 *
	 *
	    * @Title:updateAppealCase
	    * @Description:  TODO(更新诉求)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract MessageResponse updateAppealCase(AppealCase obj, List<AppealCaseFile> list) throws Exception;
    /**
	 *
	    * @Title:selectAppealCaseByPrimaryKey
	    * @Description:  TODO(获取诉求)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<AppealCase>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract SingleResult<AppealCaseVo> selectAppealCaseByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteAppealCaseByAppealCaseId
	    * @Description:  TODO(删除诉求)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-05-14
	 */
	public abstract MessageResponse deleteAppealCaseByAppealCaseId(String id,UserProp userProp) throws Exception;


	/**
	 *
	 * @Title:getList
	 * @Description:  TODO(获取列表)
	 * @param:        @throws Exception
	 * @return:       Map<String,Object>
	 * @throws
	 * @author: 陈晓克
	 * @version: 2018-05-15
	 */
	public Map<String,Object> getList(Map<String,Object> params) throws Exception;

	
}
