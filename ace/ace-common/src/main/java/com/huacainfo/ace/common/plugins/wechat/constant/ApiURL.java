package com.huacainfo.ace.common.plugins.wechat.constant;

/**
 * Created by HuaCai008 on 2018/4/16.
 */
public interface ApiURL {


    String https = "https://";
//    String http = "http://";

    /**
     * 模板消息api调用地址
     */
    String MESSAGE_TEMPLATE = https + "api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * ACCESS_TOKEN 令牌获取地址
     */
    String ACCESS_TOKEN_API_URL = https + "api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=#APPID#&secret=#APPSECRET#";

    /**
     * 菜单创建接口
     */
    String MENU_CREATE_API_URL = https + "api.weixin.qq.com/cgi-bin/menu/create?access_token=#ACCESS_TOKEN#";

    /**
     * 微信支付接口地址
     */
    String WX_PAY_PATH_API_URL = https + "api.mch.weixin.qq.com/pay/unifiedorder";


    /**
     * jsapi_ticket
     */
    String WX_JSAPI_TICKET_API_URL = https + "api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=#ACCESS_TOKEN#&type=jsapi";
}
