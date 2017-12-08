package com.huacainfo.ace.rvc.kedapi.vrs;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;
import com.huacainfo.ace.rvc.kedapi.common.base.BaseApi;
import com.huacainfo.ace.rvc.kedapi.common.kits.CommonKit;
import com.huacainfo.ace.rvc.kedapi.common.kits.HttpKit;
import com.huacainfo.ace.rvc.kedapi.common.kits.UrlKit;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LiveBroadcast;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LiveRoomResp;
import com.huacainfo.ace.rvc.kedapi.vrs.model.LocalLoginResp;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 科达录播系统
 * Created by Arvin on 2017/11/29.
 */
public class VRSApi extends BaseApi {
    private static Logger logger = Logger.getLogger(VRSApi.class);


    /**
     * 名称	本地用户登录
     * URI	/api/v1/vrs/login
     * 方法	POST
     * 说明	本地用户登录
     *
     * @param token  登陆token -- 平台那边获取的token
     * @param params 请求内容，以JSON形式发送，需进行UrlEncode
     * @return cookies
     */
    public static LocalLoginResp localLogin(String token, String params) {
//        {
//            "username": "admin",
//                "psd": "admin"
//        }
        String encode = CommonKit.encode(params);
        Map<String, String> rtnMap = HttpKit.doPost(CommonKit.VRS_URI + "/api/v1/vrs/login"
                , "account_token=" + token + "&params=" + encode
                , "");
        logger.debug("params=" + params + ", VRSApi.login:{}" + rtnMap.toString());

        return JsonUtil.toBean(rtnMap.get("resp"), LocalLoginResp.class);
    }

    /**
     * 名称	获取直播室列表
     * URI	/api/v1/vrs/liveroom
     * 方法	GET
     * 说明	获取直播室列表
     *
     * @param token    授权token
     * @param keyWords 搜索关键字
     * @param cookies  携带cookies信息
     * @return
     */
    public static LiveRoomResp getLiveRoomList(String token, String keyWords, String cookies) {
        Map<String, Object> params = new HashMap<>();
        params.put("account_token", token);//登陆token
        params.put("prgs1page", 5);//每个页面显示节目数 默认显示20个节目
        params.put("pageid", 1);//请求的页码 默认显示第1页
        params.put("includename", keyWords);//搜索字符
        /**
         * 排序引索 默认为按创建时间升序
         1-名称；
         2-创建时间；
         7-热度（节目点播次数）；
         */
        params.put("orderindex", 2);
        /**
         * 	升降序 默认为0，无orderindex则不生效
         0-升；
         1-降；
         */
        params.put("desc", 1);

        String response = HttpKit.doGet(CommonKit.VRS_URI + "/api/v1/vrs/liveroom"
                , UrlKit.getUrlParamsByMap(params)
                , cookies);
        logger.debug("keyWords<" + keyWords + "> ,VRSApi.getLiveRoomList:" + response);

        if (StringUtils.isEmpty(response)) {
            return null;
        }
        return JsonUtil.toBean(response, LiveRoomResp.class);
    }

    /**
     * 获取直播地址 m3u8文件
     *
     * @param liveRoom ‘获取直播室列表’接口返回数据
     * @return 可直播的m3u8文件URL地址
     */
    public static String getLiveBroadcast(LiveRoomResp liveRoom) {
        String jsonPath = liveRoom.getRoomstate().get(0).getLivestreampath();
        jsonPath = CommonKit.VRS_URI + jsonPath;

        String jsonData = HttpKit.loadJson(jsonPath, StringUtils.CHARSET_NAME);
        LiveBroadcast liveBroadcast = JsonUtil.toBean(jsonData, LiveBroadcast.class);

        return CommonKit.VRS_URI + liveBroadcast.getProList().get(0).getIndexList().get(0).getHlsIndex();
    }
}
