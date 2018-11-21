package com.huacainfo.ace.cu.service.impl;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.cu.dao.CuUserDao;
import com.huacainfo.ace.cu.service.WeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HuaCai008 on 2018/6/14.
 */
@Service("weChatService")
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private CuUserDao cuUserDao;

    @Override
    public ResultResponse generateNewUser(String openId) {

        return null;
    }
}
