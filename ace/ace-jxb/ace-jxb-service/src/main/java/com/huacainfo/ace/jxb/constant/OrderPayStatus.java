package com.huacainfo.ace.jxb.constant;

/**
 * @Auther: Arvin
 * @Date: 2018/8/7 10:34
 * @Description:
 */
public interface OrderPayStatus {
    /**
     * 支付状态 1-待支付 2-已付款(待接单) 3-申请退款 4-已退款 6-咨询（课程）结束/待评价 7-已完结 8-自动关闭
     */
    String NEW_ORDER = "1";
    String PAID = "2";
    String APPLY_REFUND = "3";
    String REFUNDED = "4";
    String TO_EVALUATE = "6";
    String FINISHED = "7";
    String AUTO_CLOSED = "8";
}
