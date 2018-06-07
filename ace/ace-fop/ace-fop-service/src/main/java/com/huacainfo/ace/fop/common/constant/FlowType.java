package com.huacainfo.ace.fop.common.constant;

/**
 * Created by HuaCai008 on 2018/5/4.
 */
public interface FlowType {
    /**
     * 0-企业会员申请 1-团体会员申请 2-会员缴费 3-会员退会
     */

    String MEMBER_JOIN_COMPANY = "0";
    String MEMBER_JOIN_ASSOCIATION = "1";
    String MEMBER_PAY = "2";
    String MEMBER_QUIT_COMPANY = "3";
    String MEMBER_QUIT_ASSOCIATION = "4";

    /**
     * 政企服务发布审核
     */
    String GE_HELP = "5";

    /**
     * 党建工作发布审核
     */
    String PARTY_WORK = "6";

    /**
     * 合作交流项目审核
     */
    String COOP_PROJECT = "7";

    /**
     * 诉求服务确认
     */
    String REQUEST_HELP = "8";

    /**
     * 银企服务
     */
    String FINANCE_PROJECT = "9";

    /**
     * 金融产品
     */
    String LOAN_PROJECT = "10";

    /**
     * 信息服务
     */
    String INFORMATION_SERVICE = "11";

    /**
     * 法律帮助
     */
    String LAW_HELP = "12";
}
