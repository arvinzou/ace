package com.huacainfo.ace.common.plugins.wechat.api;


import com.huacainfo.ace.common.plugins.wechat.constant.ApiURL;
import com.huacainfo.ace.common.plugins.wechat.entity.UserList;
import com.huacainfo.ace.common.plugins.wechat.entity.pojo.base.AccessToken;
import com.huacainfo.ace.common.plugins.wechat.entity.pojo.base.Menu;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Arvin on 2018/1/16.
 */
public class WeChatApi {
    public static final String SYS_ID = "hcwy";
    /**
     * 原始ID
     */
    public static final String ORIGINAL_ID = "";
    /**
     * appID
     */
    public static final String APP_ID = "";
    /**
     * app密钥
     */
    public static final String APP_SECRET = "";
    /**
     * 公众号服务平台token字符串
     */
    public static final String WX_TOKEN = "";
    /**
     * 商户企业ID
     */
    public static final String MC_ID = "";
    /**
     * 商户密钥
     */
    public static final String API_KEY = "";
    public static final String DEVICE_INFO = "WEB";
    protected static Logger logger = LoggerFactory.getLogger(WeChatApi.class);

    /***
     * 获取access_token
     *
     * @param appId APPID
     * @param appSecret APPSECRET
     * @return AccessToken
     */
    public static AccessToken getAccessToken(String appId, String appSecret) {
        String url = ApiURL.ACCESS_TOKEN_API_URL.replace("#APPID#", appId)
                .replace("#APPSECRET#", appSecret);

        String respStr = HttpKit.get(url);//demo {"access_token":"ACCESS_TOKEN","expires_in":7200 }
        logger.debug("WeChatApi.getAccessToken.appId[{}].response[ {} ]", appId, respStr);

        Map<String, Object> map = JsonUtil.toObject(respStr, Map.class);
        AccessToken accessToken = new AccessToken();
        accessToken.setToken((String) map.get("access_token"));
        accessToken.setExpiresTime((Integer) map.get("expires_in"));
        accessToken.setLastTime(DateUtil.getNow());
        //获取jsapi票据
        String jsapi = ApiURL.WX_JSAPI_TICKET_API_URL.replace("#ACCESS_TOKEN#", accessToken.getToken());
        String ticketResp = HttpKit.get(jsapi);
        logger.debug("WeChatApi.getAccessToken.ACCESS_TOKEN[{}].response[ {} ]", accessToken.getToken(), ticketResp);
//        {
//            "errcode":0,
//                "errmsg":"ok",
//                "ticket":"bxLdikRXVbTPdHSM05e5u5sUoXNKd8-41ZO3MhKoyN5OfkWITDGgnr2fwJ0m9E8NYzWKVZvdVtaUgWvsdshFKA",
//                "expires_in":7200
//        }
        Map<String, Object> mapJsapi = JsonUtil.toObject(ticketResp, Map.class);
        accessToken.setTicket((String) mapJsapi.get("ticket"));

        return accessToken;
    }


    /**
     * 创建菜单
     *
     * @param accessToken
     * @param menu
     * @return
     */
    public static String createMenu(String accessToken, Menu menu) {
        String url = ApiURL.MENU_CREATE_API_URL.replace("#ACCESS_TOKEN#", accessToken);
        return HttpKit.post(url, JsonUtil.toJson(menu));

    }

    /**
     * 获取公众号用户列表
     *
     * @param accessToken 调用接口凭证
     * @param nextOpenid  第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     */
    public static UserList getUserList(String accessToken, String nextOpenid) {
        String url = ApiURL.USER_LIST_API_URL
                .replace("#ACCESS_TOKEN#", accessToken)
                .replace("#NEXT_OPENID#", nextOpenid);
        String r = HttpKit.get(url);

        return JsonUtil.toObject(r, UserList.class);
    }
}
