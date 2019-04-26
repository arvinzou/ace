package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.LeLampStatus;
import com.huacainfo.ace.glink.vo.LeLampStatusQVo;
import com.huacainfo.ace.glink.vo.LeLampStatusVo;

import java.util.List;

/**
 * @author: Arvin
 * @version: 2019-04-25
 * @Description: TODO(弱电 - 设备状态)
 */
public interface LeLampStatusService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(弱电 - 设备状态分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<LeLampStatusVo>
     * @author: Arvin
     * @version: 2019-04-25
     */
    PageResult<LeLampStatusVo> findLeLampStatusList(LeLampStatusQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertLeLampStatus
     * @Description: TODO(添加弱电 - 设备状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-25
     */
    MessageResponse insertLeLampStatus(LeLampStatus obj, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:selectLeLampStatusByPrimaryKey
     * @Description: TODO(获取弱电 - 设备状态)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<LeLampStatus>
     * @author: Arvin
     * @version: 2019-04-25
     */
    SingleResult<LeLampStatusVo> selectLeLampStatusByPrimaryKey(String id) throws Exception;

    /**
     * 同步设备接口数据
     * @return MessageResponse
     */
    MessageResponse syncData();

    /**
     * 大屏展示数据接口 -3
     *
     * @return List<LeLampStatusVo>
     */
    List<LeLampStatusVo>  getErrorChartData();
}
