package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.DynamicScheduler;
import com.huacainfo.ace.glink.vo.DynamicSchedulerQVo;
import com.huacainfo.ace.glink.vo.DynamicSchedulerVo;

/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 调度设置)
 */
public interface DynamicSchedulerService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 调度设置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<DynamicSchedulerVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    PageResult<DynamicSchedulerVo> findDynamicSchedulerList(DynamicSchedulerQVo condition,
                                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertDynamicScheduler
     * @Description: TODO(添加故障报警 - 调度设置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse insertDynamicScheduler(DynamicScheduler obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateDynamicScheduler
     * @Description: TODO(更新故障报警 - 调度设置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse updateDynamicScheduler(DynamicScheduler obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectDynamicSchedulerByPrimaryKey
     * @Description: TODO(获取故障报警 - 调度设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<DynamicScheduler>
     * @author: Arvin
     * @version: 2019-04-11
     */
    SingleResult<DynamicSchedulerVo> selectDynamicSchedulerByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteDynamicSchedulerByDynamicSchedulerId
     * @Description: TODO(删除故障报警 - 调度设置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse deleteDynamicSchedulerByDynamicSchedulerId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse updateStatus(String id, String status, UserProp userProp) throws Exception;
}
