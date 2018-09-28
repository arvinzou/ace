package com.huacainfo.ace.society.constant;

/**
 * @Auther: Arvin
 * @Date: 2018/9/27 15:49
 * @Description:
 */
public interface OrderState {
    /**
     * 新增订单
     */
    String ORDER_STATE_NEW = "1";
    /**
     * 订单已支付
     */
    String ORDER_STATE_PAID = "2";
    /**
     * 订单已签收/已自提
     */
    String ORDER_STATE_RECEIVED = "3";
}
