package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.TrafficIllegal;
import com.huacainfo.ace.woc.vo.TrafficIllegalQVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalVo;

/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(通行违章记录)
 */
public interface TrafficIllegalService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通行违章记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<TrafficIllegalVo>
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract PageResult<TrafficIllegalVo> findTrafficIllegalList(TrafficIllegalQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTrafficIllegal
     * @Description: TODO(添加通行违章记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract MessageResponse insertTrafficIllegal(TrafficIllegal obj, UserProp userProp) throws Exception;

    public abstract MessageResponse insertTrafficIllegalII(TrafficIllegal obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTrafficIllegal
     * @Description: TODO(更新通行违章记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract MessageResponse updateTrafficIllegal(TrafficIllegal obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTrafficIllegalByPrimaryKey
     * @Description: TODO(获取通行违章记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TrafficIllegal>
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract SingleResult<TrafficIllegalVo> selectTrafficIllegalByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTrafficIllegalByTrafficIllegalId
     * @Description: TODO(删除通行违章记录)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract MessageResponse deleteTrafficIllegalByTrafficIllegalId(String id, UserProp userProp) throws Exception;

    /**
     * 违章记录转为案件
     *
     * @param ids     id1,id2,ide3   woc.traffic_illegal.id
     * @param curUser 登录管理用户信息
     * @return
     */
    MessageResponse convertToCases(String ids, UserProp curUser) throws Exception;
}
