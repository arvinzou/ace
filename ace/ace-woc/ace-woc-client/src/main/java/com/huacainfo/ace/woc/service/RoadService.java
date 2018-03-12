package com.huacainfo.ace.woc.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Road;
import com.huacainfo.ace.woc.vo.RoadVo;
import com.huacainfo.ace.woc.vo.RoadQVo;
/**
 * @author: Arvin
 * @version: 2018-03-09
 * @Description:  TODO(道路档案)
 */
public interface RoadService {
    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(道路档案分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<RoadVo>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	public abstract PageResult<RoadVo> findRoadList(RoadQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
	 *
	    * @Title:insertRoad
	    * @Description:  TODO(添加道路档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse insertRoad(Road obj,UserProp userProp) throws Exception;

    /**
	 *
	    * @Title:updateRoad
	    * @Description:  TODO(更新道路档案)
	 		* @param:        @param obj
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse updateRoad(Road obj,UserProp userProp) throws Exception;
    /**
	 *
	    * @Title:selectRoadByPrimaryKey
	    * @Description:  TODO(获取道路档案)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Road>
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	public abstract SingleResult<RoadVo> selectRoadByPrimaryKey(String id) throws Exception;
    /**
	 *
	    * @Title:deleteRoadByRoadId
	    * @Description:  TODO(删除道路档案)
	 		* @param:        @param id
	 		* @param:        @param  userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: Arvin
	    * @version: 2018-03-09
	 */
	public abstract MessageResponse deleteRoadByRoadId(String id,UserProp userProp) throws Exception;

	
}
