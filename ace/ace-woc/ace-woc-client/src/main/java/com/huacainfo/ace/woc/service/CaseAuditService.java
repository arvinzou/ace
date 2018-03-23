package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.CaseAudit;
import com.huacainfo.ace.woc.vo.CaseAuditVo;
import com.huacainfo.ace.woc.vo.CaseAuditQVo;
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(案件审核记录)
 */
public interface CaseAuditService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(案件审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CaseAuditVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract PageResult<CaseAuditVo> findCaseAuditList(CaseAuditQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertCaseAudit
	    * @Description:  TODO(添加案件审核记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse insertCaseAudit(CaseAudit obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateCaseAudit
	    * @Description:  TODO(更新案件审核记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse updateCaseAudit(CaseAudit obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectCaseAuditByPrimaryKey
	    * @Description:  TODO(获取案件审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<CaseAudit>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract SingleResult<CaseAuditVo> selectCaseAuditByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteCaseAuditByCaseAuditId
	    * @Description:  TODO(删除案件审核记录)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse deleteCaseAuditByCaseAuditId(String id,UserProp userProp) throws Exception;

	
}
