package com.huacainfo.ace.portal.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.EvaluatGauge;
import com.huacainfo.ace.portal.vo.EvaluatGaugeVo;
import com.huacainfo.ace.portal.vo.EvaluatGaugeQVo;
import java.util.Map;
/**
 * @author: 陈晓克
 * @version: 2018-06-09
 * @Description:  TODO(评测量表)
 */
public interface EvaluatGaugeService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(评测量表分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<EvaluatGaugeVo>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract PageResult<EvaluatGaugeVo> findEvaluatGaugeList(EvaluatGaugeQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertEvaluatGauge
	    * @Description:  TODO(添加评测量表)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse insertEvaluatGauge(EvaluatGauge obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateEvaluatGauge
	    * @Description:  TODO(更新评测量表)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse updateEvaluatGauge(EvaluatGauge obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectEvaluatGaugeByPrimaryKey
	    * @Description:  TODO(获取评测量表)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<EvaluatGauge>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract SingleResult<EvaluatGaugeVo> selectEvaluatGaugeByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteEvaluatGaugeByEvaluatGaugeId
	    * @Description:  TODO(删除评测量表)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
	public abstract MessageResponse deleteEvaluatGaugeByEvaluatGaugeId(String id,UserProp userProp) throws Exception;

 /**
	 *
	    * @Title:getList
	    * @Description:  TODO(查询评测量表)
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
	    * @Description:  TODO(获取评测量表)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       Map<String,Object>
	 		* @throws
	    * @author: 陈晓克
	    * @version: 2018-06-09
	 */
    public abstract Map<String,Object> getById(String id) throws Exception;

	
}
