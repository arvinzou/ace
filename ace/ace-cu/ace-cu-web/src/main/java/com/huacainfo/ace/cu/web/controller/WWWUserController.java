package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.cu.service.CuUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by HuaCai008 on 2018/6/14.
 */
@Controller
@RequestMapping("/www/user")
public class WWWUserController {

    @Autowired
    private CuUserService cuUserService;

    /**
     * 查询会员信息
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/findByOpenId")
    @ResponseBody
    public ResultResponse findByOpenId(String openId) throws Exception {
        if (StringUtil.isEmpty(openId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        return new ResultResponse(ResultCode.SUCCESS, "查询成功", cuUserService.findByOpenId(openId));

    }
}
