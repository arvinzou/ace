package com.huacainfo.ace.taa.web.controller;

import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.plugins.wechat.util.MD5;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.tools.JsonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2019/1/23 14:13
 * @Description:
 */
@RestController
@RequestMapping("/www/mapApi")
public class WMapKitController {

    /**
     * 用坐标，反向定位地点名称
     *
     * @param lat 纬度
     * @param lon 经度
     * @return Map
     */
    @RequestMapping("/tx/reversePoint")
    public Map<String, Object> reversePoint(String lat, String lon) {
        if (!StringUtil.areNotEmpty(lat, lon)) {
            return null;
        }

        final String domain = "http://apis.map.qq.com";
        String org = "/ws/geocoder/v1/?key=ALFBZ-5Z2CJ-TK6F7-KVINX-AX5L7-UFBXL&location=" + lat + "," + lon;
        final String sk = "akQ8r8Ygx4InLr5Xx99sNC969v4LXV7";
        String md5Str = MD5.MD5Encode(org + sk, "utf-8");

        String rst = HttpKit.get(domain + org + "&sig=" + md5Str);

        return JsonUtil.toMap(rst);
    }
}
