package com.huacainfo.ace.cu.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * Created by HuaCai008 on 2018/6/14.
 */
public interface WeChatService {
    ResultResponse generateNewUser(String openId);
}
