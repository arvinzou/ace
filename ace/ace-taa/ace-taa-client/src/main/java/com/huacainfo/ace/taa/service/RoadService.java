package com.huacainfo.ace.taa.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.taa.model.Road;
import com.huacainfo.ace.taa.vo.RoadVo;
import com.huacainfo.ace.taa.vo.RoadQVo;

/**
 * @author: 陈晓克
 * @version: 2019-01-03
 * @Description: TODO(道路)
 */
public interface RoadService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(道路分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <RoadVo>
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    PageResult<RoadVo> findRoadList(RoadQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertRoad
     * @Description: TODO(添加道路)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    MessageResponse insertRoad(Road obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateRoad
     * @Description: TODO(更新道路)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    MessageResponse updateRoad(Road obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectRoadByPrimaryKey
     * @Description: TODO(获取道路)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Road>
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    SingleResult<RoadVo> selectRoadByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteRoadByRoadId
     * @Description: TODO(删除道路)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-03
     */
    MessageResponse deleteRoadByRoadId(String id, UserProp userProp) throws Exception;


}
