package com.huacainfo.ace.woc.constant;

/**
 * Created by HuaCai008 on 2018/3/29.
 */
public interface CaseStatus {
    /**
     * 1 - 存盘，2 - 提交审核，3 - 案件撤销 ，4 - 案件已审核
     */

    String CASE_STATUS_SAVED = "1";

    String CASE_STATUS_SUBMIT = "2";//提交审核=待审核状态

    String CASE_STATUS_REPEALED = "3";

    String CASE_STATUS_AUDITED = "4";
}
