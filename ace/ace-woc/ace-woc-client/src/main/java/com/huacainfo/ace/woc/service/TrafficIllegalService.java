package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.TrafficIllegal;
import com.huacainfo.ace.woc.vo.TrafficIllegalVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalQVo;
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(通行违章记录)
 */
public interface TrafficIllegalService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(通行违章记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficIllegalVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract PageResult<TrafficIllegalVo> findTrafficIllegalList(TrafficIllegalQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertTrafficIllegal
	    * @Description:  TODO(添加通行违章记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse insertTrafficIllegal(TrafficIllegal obj,UserProp userProp) throws Exception;

    public abstract MessageResponse insertTrafficIllegalII(TrafficIllegal obj, UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateTrafficIllegal
	    * @Description:  TODO(更新通行违章记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse updateTrafficIllegal(TrafficIllegal obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectTrafficIllegalByPrimaryKey
	    * @Description:  TODO(获取通行违章记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TrafficIllegal>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract SingleResult<TrafficIllegalVo> selectTrafficIllegalByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteTrafficIllegalByTrafficIllegalId
	    * @Description:  TODO(删除通行违章记录)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse deleteTrafficIllegalByTrafficIllegalId(String id,UserProp userProp) throws Exception;

	
}
