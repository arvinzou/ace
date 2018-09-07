package com.huacainfo.ace.cu.common.constant;

/**
 * Created by HuaCai008 on 2018/6/19.
 */
public interface OrderConstant {

    /**
     * 订单状态
     * 0-数据逻辑删除状态，1-新增订单，2-已付款订单 3-支付异常订单 4-自动关闭订单
     */
    String ORDER_STATUS_DELETED = "0";
    String ORDER_STATUS_NEW_ORDER = "1";
    String ORDER_STATUS_PAID = "2";
    String ORDER_STATUS_ERROR = "3";
    String ORDER_STATUS_AUTO_CLOSED = "4";

    /**
     * 订单支付类型
     * 0-微信支付,
     * 1-银行卡支付
     * 2-建行龙支付
     */
    String PAY_TYPE_WX = "0";
    String PAY_TYPE_UNIONPAY = "1";
    String PAY_TYPE_CCB = "2";

}
