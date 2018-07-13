package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.service.WeChatAuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: Arvin
 * @Date: 2018/7/12 19:33
 * @Description:
 */
@RestController
@RequestMapping("/www/wechat/auth")
public class WeChatAuthorizeController extends BaseController {

    @Autowired
    private WeChatAuthorizeService weChatAuthorizeService;

    /**
     * 微信网页授权 - 第一步 - 获取授权跳转地址
     *
     * @param sysId       系统识别ID
     * @param attach      附加数据 格式为json，特殊符号，需URLEncode处理 (utf-8)
     * @param redirectUri 重定向地址
     * @return result
     */
    @RequestMapping(value = "/authorize")
    public ResultResponse authorize(String sysId, String redirectUri, String attach,
                                    HttpServletResponse response) throws Exception {
//        HttpServletRequest request, HttpServletResponse response
        //scope为base/userinfo ;base-静默跳转，userinfo需用户点击确认跳转
//        String scope = request.getParameter("scope");
//        String sysId = request.getParameter("sysId");
//        String redirectUri = request.getParameter("redirectUri");
//        //附加数据
//        String attach = request.getParameter("attach");

        if (!StringUtil.areNotEmpty(sysId)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }

        String authorizeURL = weChatAuthorizeService.authorize(sysId, redirectUri, attach);
        if (StringUtil.isEmpty(authorizeURL)) {
            return new ResultResponse(ResultCode.FAIL, "获取微信授权跳转地址失败");
        }
        //跳转授权
        response.sendRedirect(authorizeURL);
        return new ResultResponse(ResultCode.SUCCESS, "授权成功");
    }

    /**
     * 微信网页授权 - 第二步 - 获取用户信息
     *
     * @param request  请求参数
     * @param response 返回响应
     * @return result
     */
    @RequestMapping(value = "/getUserInfo")
    public String getUserInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        if (!StringUtil.areNotEmpty(code, state)) {
            return "[/wechat/getUserInfo]缺少必要参数！";
        }
        String result = weChatAuthorizeService.getUserInfo(code, state);

        //链接跳转
        if (result.startsWith("http")) {
            response.sendRedirect(result);
        }

        return result;
    }

    /**
     * 页面授权使用接口
     *
     * @param sysId 系统标示ID
     * @param code  微信跳转携带code
     * @param state 微信跳转携带state
     * @return Map
     */
    @RequestMapping(value = "/getWxUser")
    public ResultResponse getWxUser(String sysId, String code, String state) throws Exception {
        if (!StringUtil.areNotEmpty(sysId, code)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        return weChatAuthorizeService.getWxUser(sysId, code, state);
    }
}
