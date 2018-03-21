package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Case;
import com.huacainfo.ace.woc.vo.CaseVo;
import com.huacainfo.ace.woc.vo.CaseQVo;
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(案件)
 */
public interface CaseService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(案件分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CaseVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract PageResult<CaseVo> findCaseList(CaseQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertCase
	    * @Description:  TODO(添加案件)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse insertCase(Case obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateCase
	    * @Description:  TODO(更新案件)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse updateCase(Case obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectCaseByPrimaryKey
	    * @Description:  TODO(获取案件)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Case>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract SingleResult<CaseVo> selectCaseByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteCaseByCaseId
	    * @Description:  TODO(删除案件)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse deleteCaseByCaseId(String id,UserProp userProp) throws Exception;

	
}
