package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.TrafficIllegalCancel;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelQVo;
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(通行违章记录)
 */
public interface TrafficIllegalCancelService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(通行违章记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficIllegalCancelVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract PageResult<TrafficIllegalCancelVo> findTrafficIllegalCancelList(TrafficIllegalCancelQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertTrafficIllegalCancel
	    * @Description:  TODO(添加通行违章记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse insertTrafficIllegalCancel(TrafficIllegalCancel obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateTrafficIllegalCancel
	    * @Description:  TODO(更新通行违章记录)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse updateTrafficIllegalCancel(TrafficIllegalCancel obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectTrafficIllegalCancelByPrimaryKey
	    * @Description:  TODO(获取通行违章记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TrafficIllegalCancel>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract SingleResult<TrafficIllegalCancelVo> selectTrafficIllegalCancelByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteTrafficIllegalCancelByTrafficIllegalCancelId
	    * @Description:  TODO(删除通行违章记录)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
	public abstract MessageResponse deleteTrafficIllegalCancelByTrafficIllegalCancelId(String id,UserProp userProp) throws Exception;

	
}
