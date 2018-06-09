package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.EvaluatCmt;
import com.huacainfo.ace.portal.vo.EvaluatCmtVo;
import com.huacainfo.ace.portal.vo.EvaluatCmtQVo;
import java.util.Map;
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(评测评论)
 */
public interface EvaluatCmtService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测评论分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatCmtVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract PageResult<EvaluatCmtVo> findEvaluatCmtList(EvaluatCmtQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertEvaluatCmt
	    * @Description:  TODO(添加评测评论)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse insertEvaluatCmt(EvaluatCmt obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateEvaluatCmt
	    * @Description:  TODO(更新评测评论)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse updateEvaluatCmt(EvaluatCmt obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectEvaluatCmtByPrimaryKey
	    * @Description:  TODO(获取评测评论)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatCmt>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract SingleResult<EvaluatCmtVo> selectEvaluatCmtByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteEvaluatCmtByEvaluatCmtId
	    * @Description:  TODO(删除评测评论)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse deleteEvaluatCmtByEvaluatCmtId(String id,UserProp userProp) throws Exception;

 /**
	 *
	    * @Title:getList
	    * @Description:  TODO(查询评测评论)
	 		* @param:        @param params
	 		* @param:        @throws Exception
	 		* @return:       List<Map<String,Object>>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract Map<String,Object> getList(Map<String,Object> params) throws Exception;
/**
	 *
	    * @Title:getById
	    * @Description:  TODO(获取评测评论)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       Map<String,Object>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
    public abstract Map<String,Object> getById(String id) throws Exception;

	
}
