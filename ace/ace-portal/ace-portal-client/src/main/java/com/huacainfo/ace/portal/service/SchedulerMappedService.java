package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.model.SchedulerMapped;
import com.huacainfo.ace.portal.vo.SchedulerMappedQVo;
import com.huacainfo.ace.portal.vo.SchedulerMappedVo;

/**
 * @author: ArvinZou
 * @version: 2019-04-12
 * @Description: TODO(调度配置)
 */
public interface SchedulerMappedService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(调度配置分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SchedulerMappedVo>
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    PageResult<SchedulerMappedVo> findVoList(SchedulerMappedQVo condition,
                                             int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSchedulerMapped
     * @Description: TODO(添加调度配置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    MessageResponse insertSchedulerMapped(SchedulerMapped obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSchedulerMapped
     * @Description: TODO(更新调度配置)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    MessageResponse updateSchedulerMapped(SchedulerMapped obj, UserProp userProp);

    /**
     * @throws
     * @Title:selectByPrimaryKey
     * @Description: TODO(获取调度配置)
     * @param: @param id
     * @param: @throws Exception
     * @return: SchedulerMapped
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    SchedulerMapped selectByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSchedulerMappedBySchedulerMappedId
     * @Description: TODO(删除调度配置)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: ArvinZou
     * @version: 2019-04-12
     */
    MessageResponse deleteByPrimaryKey(String id, UserProp userProp) throws Exception;


}
