package com.huacainfo.ace.cu.common.constant;

/**
 * Created by HuaCai008 on 2018/6/15.
 */
public interface ProjectConstant {
    /**
     * 项目类型 0-普通项目 1-专项项目 2-个人项目 3-支出项目
     */
    String P_TYPE_NORMAL = "0";
    String P_TYPE_SPECIAL = "1";
    String P_TYPE_PERSONAL = "2";
    String P_TYPE_PAY_OUT = "3";


    /**
     * 项目状态
     * '0' : "已删除";
     * '1' : "待审核";
     * '2' : "通过";
     * '3' : "驳回";
     */
    String P_STATUS_DELETED = "0";
    String P_STATUS_PENDING = "1";
    String P_STATUS_PASSED = "2";
    String P_STATUS_REJECTED = "3";
}
