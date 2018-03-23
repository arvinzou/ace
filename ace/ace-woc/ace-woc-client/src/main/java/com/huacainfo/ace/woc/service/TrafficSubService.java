package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.TrafficSub;
import com.huacainfo.ace.woc.vo.TrafficSubVo;
import com.huacainfo.ace.woc.vo.TrafficSubQVo;
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(通行记录子表)
 */
public interface TrafficSubService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(通行记录子表分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficSubVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract PageResult<TrafficSubVo> findTrafficSubList(TrafficSubQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertTrafficSub
	    * @Description:  TODO(添加通行记录子表)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse insertTrafficSub(TrafficSub obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateTrafficSub
	    * @Description:  TODO(更新通行记录子表)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse updateTrafficSub(TrafficSub obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectTrafficSubByPrimaryKey
	    * @Description:  TODO(获取通行记录子表)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TrafficSub>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract SingleResult<TrafficSubVo> selectTrafficSubByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteTrafficSubByTrafficSubId
	    * @Description:  TODO(删除通行记录子表)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse deleteTrafficSubByTrafficSubId(String id,UserProp userProp) throws Exception;

	
}
