package com.huacainfo.ace.society.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @Auther: Arvin
 * @Date: 2018/11/23 10:04
 * @Description:
 */
public interface AuditNoticeService {

    /**
     * 送审消息通知 - 发送给管理员
     *
     * @param content 内容
     * @return ResultResponse
     */
    ResultResponse sendToAdmin(String content);

    /**
     * 审核结果通知 - 发送给提交者
     *
     * @param userId  通知用户ID
     * @param content 内容
     */
    ResultResponse sendToCustomer(String userId, String content) throws Exception;

}
