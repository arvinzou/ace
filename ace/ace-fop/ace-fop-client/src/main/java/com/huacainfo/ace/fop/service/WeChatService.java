package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.plugins.wechat.entity.msg.in.InMsg;
import com.huacainfo.ace.common.plugins.wechat.entity.msg.in.InTextMsg;
import com.huacainfo.ace.common.plugins.wechat.entity.msg.out.OutTextMsg;

/**
 * Created by HuaCai008 on 2018/6/8.
 */
public interface WeChatService {
    /**
     * 功能描述: 获取回复客服消息
     *
     * @param:
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/15 16:42
     */
    OutTextMsg getCustomerResponse(InTextMsg inTextMsg);

    /**
     * 回复欢迎语
     *
     * @param openId
     */
    OutTextMsg welcome(InMsg inMsg, String openId);
}
