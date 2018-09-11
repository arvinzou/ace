package com.huacainfo.ace.society.constant;

/**
 * @Auther: Arvin
 * @Date: 2018/9/11 18:04
 * @Description:
 */
public interface AuditState {
    /**
     * 提交审核
     */
    String SUBMIT_AUDIT = "2";

    /**
     * 审核通过
     */
    String PASS = "3";

    /**
     * 驳回
     */
    String REJECT = "4";
}
