package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.woc.service.WWWApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuaCai008 on 2018/4/23.
 */
@RestController
@RequestMapping("/www/api")
public class WWWController extends WocBaseController {

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

        String wxSessionId = this.getRequest().getHeader("WX-SESSION-ID");

        return wwwApiService.sendCmccByMobile(mobile, wxSessionId, getCurWxUser());
    }

    /**
     * 手机号码注册
     *
     * @param mobile 手机号码
     * @return
     * @throws Exception
     */
    @PostMapping("/mobileRegister")
    public ResultResponse mobileRegister(String mobile, String captcha) {
        String wxSessionId = this.getRequest().getHeader("WX-SESSION-ID");

        return wwwApiService.mobileRegister(mobile, captcha, wxSessionId, getCurWxUser());
    }
}
