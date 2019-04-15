package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Scheduler;
import com.huacainfo.ace.portal.vo.SchedulerQVo;
import com.huacainfo.ace.portal.vo.SchedulerVo;

/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 调度设置)
 */
public interface SchedulerService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 调度设置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SchedulerVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    PageResult<SchedulerVo> findSchedulerList(SchedulerQVo condition,
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
    MessageResponse insertScheduler(Scheduler obj, UserProp userProp) throws Exception;

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
    MessageResponse updateScheduler(Scheduler obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectDynamicSchedulerByPrimaryKey
     * @Description: TODO(获取故障报警 - 调度设置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Scheduler>
     * @author: Arvin
     * @version: 2019-04-11
     */
    SingleResult<SchedulerVo> selectSchedulerByPrimaryKey(String id) throws Exception;

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
    MessageResponse deleteSchedulerBySchedulerId(String id, UserProp userProp) throws Exception;

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


    /**
     * 更新调度规则有效状态
     *
     * @param id    主键
     * @param state 0-失效，1-生效
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse updateValidState(String id, String state, UserProp curUserProp);
}
