package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.result.ResultResponse;

import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/2 10:21
 * @Description:
 */
public interface MessageService {
    /**
     * 注册结果通知
     *
     * @return
     */
    ResultResponse registerMessage(boolean result, String mobile, Map<String, Object> params) throws Exception;
}
