package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.woc.service.WWWApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuaCai008 on 2018/4/23.
 */
@RestController
@RequestMapping("/www/api")
public class WWWController extends WocBaseController {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private WWWApiService wwwApiService;


    /**
     * 手机号码注册前，发送手机验证码
     *
     * @param mobile 手机号码
     * @return
     * @throws Exception
     */
    @PostMapping("/sendCmccByMobile")
    public ResultResponse sendCmccByMobile(String mobile) throws Exception {

        String sessionId = this.getRequest().getHeader("WX-SESSION-ID");
        if (CommonUtils.isEmpty(sessionId)) {
            sessionId = this.getRequest().getSession().getId();
            //            return new ResultResponse(ResultCode.FAIL, "非法登录用户");
        }
        logger.info("=================== sendCmccByMobile session id is [{}]", sessionId);
        logger.info("=================== sendCmccByMobile session Object [{}]", JsonUtil.toJson(getCurUserinfo()));

        String openId = "";
        if (null != getCurWxUser()) {
            openId = getCurWxUser().getOpenId();
        } else if (null != getCurUserinfo()) {
            openId = getCurUserinfo().getOpenid();
        }

        return wwwApiService.sendCmccByMobile(mobile, sessionId, openId);
    }

    /**
     * 手机号码注册
     *
     * @param mobile  手机号码
     * @param captcha 验证码
     * @return
     * @throws Exception
     */
    @PostMapping("/mobileRegister")
    public ResultResponse mobileRegister(String mobile, String captcha) {
        String sessionId = this.getRequest().getHeader("WX-SESSION-ID");
        if (CommonUtils.isEmpty(sessionId)) {
            sessionId = this.getRequest().getSession().getId();
            //            return new ResultResponse(ResultCode.FAIL, "非法登录用户");
        }
        String openId = "";
        if (null != getCurWxUser()) {
            openId = getCurWxUser().getOpenId();
        } else if (null != getCurUserinfo()) {
            openId = getCurUserinfo().getOpenid();
        }

        return wwwApiService.mobileRegister(mobile, captcha, sessionId, openId);
    }


    /**
     * 查询车牌对应违章记录
     *
     * @param captcha 验证码
     * @param mobile  车主手机号码
     * @param plateNo 车牌号
     * @return
     * @throws Exception
     */
    @GetMapping("/findIllegalTraffic")
    public ResultResponse findIllegalTraffic(String captcha, String mobile, String plateNo) throws Exception {
        String sessionId = this.getRequest().getHeader("WX-SESSION-ID");
        if (CommonUtils.isBlank(sessionId)) {
            sessionId = this.getRequest().getSession().getId();
            //            return new ResultResponse(ResultCode.FAIL, "非法登录用户");
        }
        String openId = "";
        if (null != getCurWxUser()) {
            openId = getCurWxUser().getOpenId();
        } else if (null != getCurUserinfo()) {
            openId = getCurUserinfo().getOpenid();
        }

        return wwwApiService.findIllegalTraffic(captcha, mobile, plateNo, sessionId, openId);
    }

    /**
     * 获取在库微信用户信息
     *
     * @return
     */
    @GetMapping("/getWxUserInfo")
    public ResultResponse getWxUserInfo() {
        String openId = "";
        if (null != getCurWxUser()) {
            openId = getCurWxUser().getOpenId();
        } else if (null != getCurUserinfo()) {
            openId = getCurUserinfo().getOpenid();
        }

        return wwwApiService.getWxUserInfo(openId);
    }

    /**
     * 获取在库用户信息
     *
     * @return
     */
    @GetMapping("/getPerson")
    public ResultResponse getPersonInfo() {
        String openId = "";
        if (null != getCurWxUser()) {
            openId = getCurWxUser().getOpenId();
        } else if (null != getCurUserinfo()) {
            openId = getCurUserinfo().getOpenid();
        }
        return wwwApiService.getPersonInfo(openId);
    }

    /**
     * 解除微信手机号码绑定
     *
     * @return
     */
    @PostMapping("/unbindMobile")
    public ResultResponse unbindMobile() {
        String openId = "";
        if (null != getCurWxUser()) {
            openId = getCurWxUser().getOpenId();
        } else if (null != getCurUserinfo()) {
            openId = getCurUserinfo().getOpenid();
        }
        return wwwApiService.unbindMobile(openId);
    }
}
