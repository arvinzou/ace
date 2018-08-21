package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.model.BaseOrder;

/**
 * @Auther: Arvin
 * @Date: 2018/8/21 11:32
 * @Description:
 */
public interface BisMsgNoticeService {
    /**
     * 付款成功消息
     */
    ResultResponse paySuccess(BaseOrder order);
}
