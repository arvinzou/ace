package com.huacainfo.ace.jxb.service.impl;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.jxb.model.BaseOrder;
import com.huacainfo.ace.jxb.service.BisMsgNoticeService;
import org.springframework.stereotype.Service;

/**
 * @Auther: Arvin
 * @Date: 2018/8/21 11:44
 * @Description:
 */
@Service("bisMsgNoticeService")
public class BisMsgNoticeServiceImpl implements BisMsgNoticeService {

    /**
     * 付款成功消息
     *
     * @param order 订单信息
     */
    @Override
    public ResultResponse paySuccess(BaseOrder order) {

        return null;
    }
}
