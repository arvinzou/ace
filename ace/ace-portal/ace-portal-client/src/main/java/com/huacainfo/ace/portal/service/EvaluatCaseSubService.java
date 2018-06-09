package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.EvaluatCaseSub;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubVo;
import com.huacainfo.ace.portal.vo.EvaluatCaseSubQVo;
import java.util.Map;
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(题目选项)
 */
public interface EvaluatCaseSubService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(题目选项分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatCaseSubVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract PageResult<EvaluatCaseSubVo> findEvaluatCaseSubList(EvaluatCaseSubQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertEvaluatCaseSub
	    * @Description:  TODO(添加题目选项)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse insertEvaluatCaseSub(EvaluatCaseSub obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateEvaluatCaseSub
	    * @Description:  TODO(更新题目选项)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse updateEvaluatCaseSub(EvaluatCaseSub obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectEvaluatCaseSubByPrimaryKey
	    * @Description:  TODO(获取题目选项)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatCaseSub>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract SingleResult<EvaluatCaseSubVo> selectEvaluatCaseSubByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteEvaluatCaseSubByEvaluatCaseSubId
	    * @Description:  TODO(删除题目选项)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse deleteEvaluatCaseSubByEvaluatCaseSubId(String id,UserProp userProp) throws Exception;

 /**
	 *
	    * @Title:getList
	    * @Description:  TODO(查询题目选项)
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
	    * @Description:  TODO(获取题目选项)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       Map<String,Object>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
    public abstract Map<String,Object> getById(String id) throws Exception;

	
}
