package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.plugins.wechat.constant.WeChatConstants;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.SnsAccessTokenUtil;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.service.OAuth2Service;
import com.huacainfo.ace.portal.service.WeChatAuthorizeService;
import com.huacainfo.ace.portal.service.WxCfgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/7/12 19:43
 * @Description:
 */
@Service("weChatAuthorizeService")
public class WeChatAuthorizeServiceImpl implements WeChatAuthorizeService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private WxCfgService wxCfgService;
    @Autowired
    private OAuth2Service oAuth2Service;

    /**
     * 功能描述: 微信网页授权 - 第一步 - 获取授权跳转地址
     *
     * @param scope       scope为base/userinfo ;base-静默跳转，userinfo需用户点击确认跳转
     * @param sysId       系统识别ID
     * @param attach      附加数据 格式为json，特殊符号，需URLEncode处理 (utf-8)
     * @param redirectUri
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/7/12 19:44
     */
    @Override
    public String authorize(String sysId, String redirectUri, String attach) {
        WxCfg wxCfg = wxCfgService.findBySysId(sysId);
        if (null == wxCfg) {
            return "";
        }
//        String redirectUri = (String) attachMap.get("redirectUri"); //domain + "/www/wechat/auth/getUserInfo";
        if (StringUtil.isEmpty(redirectUri)) {
            if (StringUtil.isNotEmpty(attach)) {
                attach = decode(attach);
            }
            Map<String, Object> attachMap = JsonUtil.toMap(attach);
            logger.debug("=======wechat authorize========attachMap: {} ", attachMap);

            String domain = (String) attachMap.get("domain");
            redirectUri = domain + "/www/wechat/auth/getUserInfo";
        }

        logger.debug("=======wechat authorize========redirectUri: {}", redirectUri);
        String state = sysId + "|";

        return SnsAccessTokenUtil.getAuthorizeURL(wxCfg.getAppId(), redirectUri, state, true);
    }

    /**
     * 功能描述: 微信网页授权 - 第二步 - 获取用户信息
     *
     * @param code  授权码
     * @param state 附加信息
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/7/12 19:59
     */
    @Override
    public String getUserInfo(String code, String state) {
        String[] stateArray = state.split("\\|");
        String sysId = stateArray[0];//系统识别代码

        WxCfg wxCfg = wxCfgService.findBySysId(sysId);
        if (null == wxCfg) {
            logger.debug("公众号配置信息读取失败");
            return "";
        }
        //微信授权，获取用户信息 -- UnionID机制（先网页授权，再UnionID机制）
        Userinfo userInfo = getUserInfo(code, wxCfg);
        if (null == userInfo) {
            logger.debug("[UnionID机制]微信用户获取失败");
            return "";
        }

        return userInfo.getOpenid();
    }

    /**
     * 页面授权使用接口
     *
     * @param sysId 系统标示ID
     * @param code  微信跳转携带code
     * @param state 微信跳转携带state
     * @return Map
     */
    @Override
    public ResultResponse getWxUser(String sysId, String code, String state) throws Exception {
        WxCfg wxCfg = wxCfgService.findBySysId(sysId);
        if (null == wxCfg) {
            return new ResultResponse(ResultCode.FAIL, "公众号配置信息读取失败");
        }
        //微信授权，获取用户信息
        SingleResult<Userinfo> rst =
                oAuth2Service.saveOrUpdateUserinfo(wxCfg.getAppId(), wxCfg.getAppScret(), code, state);
        this.logger.info("===WeChatAuthorizeService.getWxUser==={}", rst.getErrorMessage());
        if (rst.getState()) {
//            this.getRequest().getSession().setAttribute(CommonKeys.SESSION_USERINFO_KEY, rst.getValue());

            Map<String, Object> rtnMap = new HashMap<>();
            Userinfo userinfo = rst.getValue();
            rtnMap.put("nickName", userinfo.getNickname());
            rtnMap.put("headimgurl", userinfo.getHeadimgurl());
            rtnMap.put("openid", userinfo.getOpenid());
            rtnMap.put("unionid", userinfo.getUnionid());

            return new ResultResponse(ResultCode.SUCCESS, "获取成功", rtnMap);
        }

        return new ResultResponse(ResultCode.FAIL, rst.getErrorMessage());
    }


    private Userinfo getUserInfo(String code, WxCfg wxCfg) {
        String openId = getOpenIdByCode(code, wxCfg);
        if (StringUtil.isEmpty(openId)) {
            return null;
        }
        String token = wxCfg.getAccessToken();
        if (StringUtil.isNotEmpty(token)) {
            String url = WeChatConstants.TAKE_USER_INFO_URL
                    .replace("ACCESS_TOKEN", token)
                    .replace("OPENID", openId);
            String result = HttpKit.get(url);

            logger.debug("WeChatUtil.TakeUserInfo.result={}", result);
            return JsonUtil.toObject(result, Userinfo.class);
        }

        return null;
    }

    private String getOpenIdByCode(String code, WxCfg wxCfg) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                "?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        url = String.format(url, wxCfg.getAppId(), wxCfg.getAppScret(), code);
        String openId = "";
        for (int i = 0; i < 3; i++) {
            try {
                String jsonResult = HttpKit.post(url, "1");
                logger.debug("getOpenIdByCode.jsonResult={}", jsonResult);
                Map<String, Object> map = JsonUtil.toMap(jsonResult);
                if (map.containsKey("openid")) {
                    openId = String.valueOf(map.get("openid"));
                    return openId;
                }
                if (StringUtil.isNotEmpty(openId)) {
                    break;
                } else if (map.containsKey("errcode")) {
                    logger.error("getOpenIdByCode的第{}次.result = {}", i + 1, jsonResult);
                    break;
                } else {
                    logger.error("getOpenIdByCode的第{}次.openId is null,code={},,result = {}", i + 1, code, jsonResult);
                }
            } catch (Exception ex) {
                logger.error("getOpenIdByCode.error", ex);
            }
        }
        return openId;
    }

    private String decode(String str) {
        try {
            str = URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return str;
    }
}
