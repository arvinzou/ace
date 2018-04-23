package com.huacainfo.ace.woc.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.service.WWWApiService;
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
     * @param mobile  手机号码
     * @param captcha 验证码
     * @return
     * @throws Exception
     */
    @PostMapping("/mobileRegister")
    public ResultResponse mobileRegister(String mobile, String captcha) {
        String wxSessionId = this.getRequest().getHeader("WX-SESSION-ID");

        return wwwApiService.mobileRegister(mobile, captcha, wxSessionId, getCurWxUser());
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
        String wxSessionId = this.getRequest().getHeader("WX-SESSION-ID");
        if (CommonUtils.isBlank(wxSessionId)) {
            return new ResultResponse(ResultCode.FAIL, "非法调用用户");
        }

        return wwwApiService.findIllegalTraffic(captcha, mobile, plateNo, wxSessionId, getCurWxUser());
    }
}
