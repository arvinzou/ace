package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;

import java.util.Map;

/**
 * @author: 陈晓克
 * @version: 2019-01-12
 * @Description: TODO(公告通知)
 */
public interface NoticeStatusService {

    /**
     * @throws
     * @Title:updateStatus
     * @Description: TODO(更新状态)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-12
     */
    MessageResponse updateStatus(String id, UserProp userProp) throws Exception;


    /**
     * @throws
     * @Title:insertNoticeStatus
     * @Description: TODO(批量插入)
     * @param: @param id
     * @param: @param userIds
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2019-01-16
     */
    MessageResponse insertNoticeStatus(String id, String[] userIds, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:getPushUsersList
     * @Description: TODO(获取已经发送人员列表)
     * @param: @param id
     * @param: @throws Exception
     * @return: ListResult<Map<String,Object>>
     * @author: 陈晓克
     * @version: 2019-01-16
     */
    ListResult<Map<String, Object>> getPushUsersList(String id) throws Exception;
}
