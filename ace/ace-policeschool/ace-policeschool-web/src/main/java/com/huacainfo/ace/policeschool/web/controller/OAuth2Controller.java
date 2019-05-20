package com.huacainfo.ace.policeschool.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.util.SnsAccessTokenUtil;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import com.huacainfo.ace.common.tools.TimestampKit;
import com.huacainfo.ace.policeschool.constant.CommConstant;
import com.huacainfo.ace.policeschool.service.SignService;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.OAuth2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
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
    private final String LOGIN_PAGE = CommConstant.LOGIN_PAGE;//"/policeschool/www/login/index.jsp?";
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
            setSessionAttr(CommonKeys.SESSION_USERINFO_KEY, rst.getValue());

            logger.debug("========================referer[state]: {}", state + "&sysId=policeschool");
            response.sendRedirect(state + "&sysId=policeschool");
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
                               HttpServletRequest request, HttpServletResponse response) {

        if (!StringUtil.areNotEmpty(action, respUri)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        //
        try {
            String appid = PropertyUtil.getProperty("appid");
            String auth2_uri = PropertyUtil.getProperty("auth2_uri");
            state = action + "|" + respUri + "|" + jsonData;
            String redirectUri = SnsAccessTokenUtil.getAuthorizeURL(appid, auth2_uri, state, false);
            response.sendRedirect(redirectUri);
            return new ResultResponse(ResultCode.SUCCESS, "success");
        } catch (Exception e) {

            logger.error(CommConstant.SMS_SIGN_STR + "微信授权异常,error-> {}", e);
            return new ResultResponse(ResultCode.FAIL, "系统异常" + e.getMessage());
        }
    }

    @RequestMapping("/auth2")
    public void auth2(String code,
                      String state,
                      HttpServletRequest request,
                      HttpServletResponse response) {
        String domain = PropertyUtil.getProperty("fastdfs_server");
        if (domain.endsWith("/")) {
            domain = domain.substring(0, domain.length() - 1);
        }
        String defaultUri = domain + CommConstant.LOGIN_PAGE + "timestamp=" + TimestampKit.getTimestamp13();
        try {
            String respUri;//跳转地址
            if (!StringUtil.areNotEmpty(code, state)) {
                respUri = URLEncoder.encode(LOGIN_PAGE + "error=授权code获取失败", "UTF-8");
                response.sendRedirect(respUri);
                return;
            }
            this.logger.info(CommConstant.SMS_SIGN_STR + "code->{} state -> {}", code, state);
            SingleResult<Userinfo> rst = this.oAuth2Service.saveOrUpdateUserinfo(appid, secret, code, state);
            if (rst.getState()) {

                this.logger.info("**************OAuth2Controller 【1】 **************");

                //doAction
                String[] stateData = state.split("\\|");
                String action = stateData[0];
                respUri = stateData[1];
                respUri = StringUtil.isNotEmpty(respUri) ? URLDecoder.decode(respUri, "utf-8") : "";
                String jsonData = stateData[2];
                Map<String, Object> pageParams = new HashMap<>();
                if (StringUtil.isNotEmpty(jsonData)) {
                    pageParams = JsonUtil.toMap(jsonData);
                }
                this.logger.info("**************OAuth2Controller 【2】 **************");
                Userinfo userinfo = rst.getValue();//微信个人资料
                ResultResponse rs = new ResultResponse(ResultCode.SUCCESS, "操作成功");
                switch (action) {
                    case "WX_BIND"://绑定微信
                        respUri = bindWx(userinfo, respUri, pageParams);
                        break;
                    case "WX_LOGIN"://微信登录
                        respUri = wxLogin(userinfo, respUri);
                        break;
                    default:
                        respUri = LOGIN_PAGE + "error=" + URLEncoder.encode("未知处理类型", "UTF-8");//错误提醒
                        break;
                }

            } else {
                logger.error("存储微信个人资料失败");
                respUri = LOGIN_PAGE + "error=" + URLEncoder.encode("存储微信个人资料失败", "UTF-8");//错误提醒
            }
            this.logger.info("**************OAuth2Controller 【3】 **************");
            respUri = domain + respUri;
            if (respUri.lastIndexOf("?") == -1) {
                respUri += "?timestamp=" + TimestampKit.getTimestamp13();
            } else if (respUri.lastIndexOf("?") != respUri.length() - 1) {

                respUri += "&timestamp=" + TimestampKit.getTimestamp13();
            } else {
                respUri += "timestamp=" + TimestampKit.getTimestamp13();
            }
            logger.info("**************respUri-> {}", respUri);
            //返回目标请求地址
            if (StringUtil.isNotEmpty(respUri)) {
                response.sendRedirect(respUri);
                return;
            } else {
                logger.info("无返回跳转地址");
            }
        } catch (Exception e) {
            try {
                response.sendRedirect(defaultUri);
            } catch (IOException e1) {
            }
            logger.error(CommConstant.SMS_SIGN_STR + "微信授权异常,error-> {}", e);
            return;
        }
    }

    private String wxLogin(Userinfo userinfo, String respUri) throws UnsupportedEncodingException {
        ResultResponse rs = signService.wxLogin(userinfo.getUnionid());
        if (ResultCode.FAIL == rs.getStatus()) {
            respUri = LOGIN_PAGE + "error=" + URLEncoder.encode(rs.getInfo(), "UTF-8");//错误提醒
        } else {
            //登录session注册
            registerSession((Users) rs.getData());
        }

        return respUri;
    }

    private String bindWx(Userinfo userinfo, String respUri, Map<String, Object> pageParams) throws UnsupportedEncodingException {
        ResultResponse rs = signService.wxBind((String) pageParams.get("account"), userinfo.getUnionid());
        if (ResultCode.FAIL == rs.getStatus()) {
            //错误提醒
            respUri = LOGIN_PAGE + "error=" + URLEncoder.encode(rs.getInfo(), "UTF-8");
        } else {
            //微信登录
            rs = signService.wxLogin(userinfo.getUnionid());
            if (ResultCode.FAIL == rs.getStatus()) {
                //错误提醒
                respUri = LOGIN_PAGE + "error=" + URLEncoder.encode(rs.getInfo(), "UTF-8");
            } else {
                //登录session注册
                registerSession((Users) rs.getData());
            }
        }

        return respUri;
    }

}
