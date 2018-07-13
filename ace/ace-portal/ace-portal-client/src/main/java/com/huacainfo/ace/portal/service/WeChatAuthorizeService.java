package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * @Auther: Arvin
 * @Date: 2018/7/12 19:42
 * @Description:
 */
public interface WeChatAuthorizeService {

    /**
     * 功能描述: 微信网页授权 - 第一步 - 获取授权跳转地址
     *
     * @param sysId       系统识别ID
     * @param attach      附加数据 格式为json，特殊符号，需URLEncode处理 (utf-8)
     * @param redirectUri 重定向地址
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/7/12 19:44
     */
    String authorize(String sysId, String redirectUri, String attach);

    /**

     */
    /**
     * 功能描述: 微信网页授权 - 第二步 - 获取用户信息
     *
     * @param code  授权码
     * @param state 附加信息
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/7/12 19:59
     */
    String getUserInfo(String code, String state);

    /**
     * 页面授权使用接口
     *
     * @param sysId 系统标示ID
     * @param code  微信跳转携带code
     * @param state 微信跳转携带state
     * @return Map
     */
    ResultResponse getWxUser(String sysId, String code, String state) throws Exception;
}
