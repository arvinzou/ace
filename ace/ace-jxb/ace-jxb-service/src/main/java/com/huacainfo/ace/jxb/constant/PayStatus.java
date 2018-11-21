package com.huacainfo.ace.jxb.constant;

/**
 * @Auther: Arvin
 * @Date: 2018/7/30 16:11
 * @Description:
 */
public interface PayStatus {

    /**
     * 支付状态 1-待支付 2-已付款(待接单) 3-申请退款 4-已退款 5-咨询（课程）结束/待评价 6-已完结 7-自动关闭
     */
    String NEW_ORDER = "1";
    String PAID = "2";
    String APPLY_REFUND = "3";
    String REFUNDED = "4";
    String TO_BE_EVALUATED = "5";
    String FINISH = "6";
    String AUTO_CLOSED = "7";
}
