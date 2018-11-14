package com.huacainfo.ace.jxb.constant;

/**
 * @Auther: Arvin
 * @Date: 2018/11/14 13:59
 * @Description:
 */
public interface AuditConstant {

    /**
     * 提现审核 状态
     */
    String TEMP = "temp";//提交审核
    String PASS = "pass";//审核通过 -- 提现成功
    String REJECT = "reject";//驳回审核 -- 提现失败
}
