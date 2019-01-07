package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.SnsAccessTokenUtil;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.partyschool.service.SignService;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.OAuth2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;

@RestController
@RequestMapping("/www/oauth2")
/**
 * @author: 陈晓克
 * @version: 2017-12-27
 * @Description:
 */
public class OAuth2Controller extends BisBaseController {

    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("#{config[appid]}")
    private String appid;
    @Value("#{config[secret]}")
    private String secret;
    @Value("#{config[redirect_uri]}")
    private String redirect_uri;
    @Value("#{config[scope]}")
    private String scope;
    @Value("#{config[state]}")
    private String state;

    @Autowired
    private OAuth2Service oAuth2Service;
    @Autowired
    private SignService signService;

    @RequestMapping(value = "/redirect.do")
    public void redirect(String code, String state,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.logger.info("code->{} state -> {}", code, state);
        logger.info("=========================  start get Userinfo from weixin pltfrom======================");
        SingleResult<Userinfo> rst = this.oAuth2Service.saveOrUpdateUserinfo(appid, secret, code, state);
        this.logger.info("{}", rst.getErrorMessage());
        if (rst.getState()) {
            this.logger.info("==================={}  in session ======================", rst.getValue().getNickname());
//        USERINFO   session setAttribute
            sessionSet(CommonKeys.SESSION_USERINFO_KEY, rst.getValue());

            logger.debug("========================referer[state]: {}", state + "&sysId=partyschool");
            response.sendRedirect(state + "&sysId=partyschool");
        } else {
            response.sendRedirect(request.getContextPath() + "/www/view/me/error.jsp");
        }
    }

    /***
     * 页面授权 一次调用地址
     * @param action 处理类型
     * @param respUri 需要返回的目标uri
     * @param jsonData 携带参数  json格式参数
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return
     * @throws IOException
     */
    @RequestMapping("/auth")
    public ResultResponse auth(String action, String respUri, String jsonData,
                               HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!StringUtil.areNotEmpty(action, respUri)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //
        String appid = PropertyUtil.getProperty("appid");
        String auth2_uri = PropertyUtil.getProperty("auth2_uri");
        state = action + "|" + respUri + "|" + jsonData;
        String redirectUri = SnsAccessTokenUtil.getAuthorizeURL(appid, auth2_uri, state, true);
        response.sendRedirect(redirectUri);

        return new ResultResponse(ResultCode.SUCCESS, "success");
    }

    @RequestMapping("/auth2")
    public ResultResponse auth2(String code, String state,
                                HttpServletRequest request, HttpServletResponse response) throws Exception {

        if (!StringUtil.areNotEmpty(code, state)) {
            return new ResultResponse(ResultCode.FAIL, "授权code获取失败");
        }

        this.logger.info("【市委党校】code->{} state -> {}", code, state);
        SingleResult<Userinfo> rst = this.oAuth2Service.saveOrUpdateUserinfo(appid, secret, code, state);
        if (rst.getState()) {
            //doAction
            String[] stateData = state.split("\\|");
            String action = stateData[0];
            String respUri = stateData[1];
            String jsonData = stateData[2];
            Map<String, Object> pageParams = JsonUtil.toMap(jsonData);
            Userinfo userinfo = rst.getValue();//微信个人资料
            ResultResponse rs;
            switch (action) {
                case "WX_BIND"://绑定微信
                    rs = signService.wxBind((String) pageParams.get("account"), userinfo.getUnionid());
                    if (ResultCode.FAIL == rs.getStatus()) {
                        return rs;
                    }
                    break;
                case "WX_LOGIN"://微信登录
                    rs = signService.wxLogin(userinfo.getUnionid());
                    if (ResultCode.FAIL == rs.getStatus()) {
                        return rs;
                    } else {
                        //登录session注册
                        registerSession((Users) rs.getData());
                    }
                    break;
                default:
                    return new ResultResponse(ResultCode.FAIL, "未知处理类型");
            }
            //返回目标请求地址
            if (StringUtil.isNotEmpty(respUri)) {
                respUri = URLDecoder.decode(respUri, "UTF-8");
                response.sendRedirect(respUri);
            }
            return new ResultResponse(ResultCode.SUCCESS, "success");
        } else {
            logger.error("存储微信个人资料失败");
            return new ResultResponse(ResultCode.FAIL, "存储微信个人资料失败");
        }
    }
}
