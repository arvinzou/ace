package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.woc.model.Traffic;
import com.huacainfo.ace.woc.vo.TrafficQVo;
import com.huacainfo.ace.woc.vo.TrafficVo;

import java.util.Map;

/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(案件审核记录)
 */
public interface TrafficService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(案件审核记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<TrafficVo>
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract PageResult<TrafficVo> findTrafficList(TrafficQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTraffic
     * @Description: TODO(添加案件审核记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract MessageResponse insertTraffic(Traffic obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTraffic
     * @Description: TODO(更新案件审核记录)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract MessageResponse updateTraffic(Traffic obj, UserProp userProp) throws Exception;

    public abstract MessageResponse updateTrafficStatus(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTrafficByPrimaryKey
     * @Description: TODO(获取案件审核记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Traffic>
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract SingleResult<TrafficVo> selectTrafficByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTrafficByTrafficId
     * @Description: TODO(删除案件审核记录)
     * @param: @param id
     * @param: @param  userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    public abstract MessageResponse deleteTrafficByTrafficId(String id, UserProp userProp) throws Exception;


    Map<String, Object> selectListByKeyWord(String keyWord, String id);

    /**
     * 创建模拟数据
     *
     * @param date     日期时间 yyyy-MM-dd hh:mm:ss
     * @param num      一次构建的数据条数
     * @param userProp 当前登录用户信息
     * @return 处理结果
     */
    MessageResponse createData(String date, String num, UserProp userProp);

    MessageResponse buildIllegalTrafficData(Map<String, Object> params, UserProp userProp) throws Exception;
}
