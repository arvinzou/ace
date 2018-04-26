package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.result.ResultResponse;

/**
 * Created by HuaCai008 on 2018/4/23.
 */
public interface WWWApiService {


    /**
     * 手机号码注册前，发送手机验证码
     *
     * @param mobile      手机号码
     * @param wxSessionId
     * @param openid   当前微信用户信息
     * @return
     * @throws Exception
     */
    ResultResponse sendCmccByMobile(String mobile, String wxSessionId, String openid) throws Exception;

    /**
     * 手机号码注册
     *
     * @param mobile  手机号码
     * @param captcha 验证码
     * @return
     * @throws Exception
     */
    ResultResponse mobileRegister(String mobile, String captcha, String sessionId, String openid);

    /**
     * 查询车牌对应违章记s录
     *
     * @param captcha     验证码
     * @param mobile      车主手机号码
     * @param plateNo     车牌号
     * @param sessionId
     * @return
     */
    ResultResponse findIllegalTraffic(String captcha, String mobile, String plateNo, String sessionId, String openid);

    /**
     * 获取在库微信用户信息
     *
     * @return
     */
    ResultResponse getWxUserInfo(String openid);

    /**
     * 查询个人信息  -- woc.person
     * @param openId
     * @return
     */
    ResultResponse getPersonInfo(String openId);

    /**
     * 解除微信手机绑定
     *
     * @return
     */
    ResultResponse unbindMobile(String openId);
}
