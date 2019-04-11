package com.huacainfo.ace.glink.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.glink.model.SmsSchedulerMapped;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedQVo;
import com.huacainfo.ace.glink.vo.SmsSchedulerMappedVo;

/**
 * @author: Arvin
 * @version: 2019-04-11
 * @Description: TODO(故障报警 - 短信 - 调度映射关系)
 */
public interface SmsSchedulerMappedService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(故障报警 - 短信 - 调度映射关系分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SmsSchedulerMappedVo>
     * @author: Arvin
     * @version: 2019-04-11
     */
    PageResult<SmsSchedulerMappedVo> findSmsSchedulerMappedList(SmsSchedulerMappedQVo condition,
                                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertSmsSchedulerMapped
     * @Description: TODO(添加故障报警 - 短信 - 调度映射关系)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse insertSmsSchedulerMapped(SmsSchedulerMapped obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateSmsSchedulerMapped
     * @Description: TODO(更新故障报警 - 短信 - 调度映射关系)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse updateSmsSchedulerMapped(SmsSchedulerMapped obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectSmsSchedulerMappedByPrimaryKey
     * @Description: TODO(获取故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SmsSchedulerMapped>
     * @author: Arvin
     * @version: 2019-04-11
     */
    SingleResult<SmsSchedulerMappedVo> selectSmsSchedulerMappedByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteSmsSchedulerMappedBySmsSchedulerMappedId
     * @Description: TODO(删除故障报警 - 短信 - 调度映射关系)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-11
     */
    MessageResponse deleteSmsSchedulerMappedBySmsSchedulerMappedId(String id, UserProp userProp) throws Exception;

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
