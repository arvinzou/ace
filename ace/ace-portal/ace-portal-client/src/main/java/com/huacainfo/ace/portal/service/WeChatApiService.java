package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @Auther: Arvin
 * @Date: 2018/11/12 14:16
 * @Description:
 */
public interface WeChatApiService {
    /**
     * 同步 公众号用户列表
     *
     * @param sysId 系统id wx_cfg.sysId
     * @return ResultResponse
     */
    ResultResponse synUserList(String sysId) throws Exception;
}
