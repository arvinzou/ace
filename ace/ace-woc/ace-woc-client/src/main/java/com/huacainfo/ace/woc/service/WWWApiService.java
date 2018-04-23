package com.huacainfo.ace.woc.service;

import com.huacainfo.ace.common.model.WxUser;
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
     * @param curWxUser   当前微信用户信息  @return
     * @throws Exception
     */
    ResultResponse sendCmccByMobile(String mobile, String wxSessionId, WxUser curWxUser) throws Exception;


    /**
     * 手机号码注册
     *
     * @param mobile  手机号码
     * @param captcha 验证码
     * @return
     * @throws Exception
     */
    ResultResponse mobileRegister(String mobile, String captcha, String wxSessionId, WxUser curWxUser);
}
