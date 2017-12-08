package com.huacainfo.ace.rvc.kedapi.common.kits;

import com.huacainfo.ace.common.tools.JsonUtil;
import com.huacainfo.ace.common.tools.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 公用常量
 * @author: ArvinZou
 * @create: 2017-11-17 10:01
 */
public class CommonKit {

    /***
     * 失败返回
     * @param errCode 错误代码
     * @param errMessage 错误提示
     * @return fail
     */
    public static String fail(String errCode, String errMessage) {
        if (StringUtils.isEmpty(errCode) && StringUtils.isEmpty(errMessage)) {
            return fail();
        } else {
            Map<String, String> map = new HashMap<>();
            map.put("errCode", errCode);
            map.put("errMessage", errMessage);

            return JsonUtil.toJson(map);
        }

    }

    /***
     * 失败返回
     * @return fail
     */
    public static String fail() {
        return "fail";
    }

    /**
     * 成功返回
     *
     * @return success
     */
    public static String success() {
        return "success";
    }

    /**
     * 成功返回
     *
     * @param obj 存储对象
     * @return Map<String,Object>
     * {    "success": 0,    "error_code": 112230    }
     */
    public static String success(Object obj) {
        Map<String, Object> map = new HashMap<>();
        map.put("success", 1);
        map.put("data", obj);

        return JsonUtil.toJson(map);
    }

    /***
     *
     * URLEncode字符串编码
     * @param str 带处理字符串
     * @return 处理后字符串/""
     */
    public static String encode(String str) {
        try {
            String encode = URLEncoder.encode(str, StringUtils.CHARSET_NAME);

            return encode;
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }


    /**
     * 解析cookie串，单独获取 SSO_COOKIE_KEY
     *
     * @param cookieStr cookie串
     * @return SSO_COOKIE_KEY= ""
     */
    public static String getSsoCookieKey(String cookieStr) {
//        eos_style_cookie=default; SSO_COOKIE_KEY=""; Domain=192.168.20.240; Expires=Thu, 01-Jan-1970 00:00:10 GMT; Path=/
        String[] strArray = cookieStr.split(";");
        String[] tempArray;
        for (String str : strArray) {
            str = str.trim();
            tempArray = str.split("=");
            for (String s : tempArray) {
                if ("SSO_COOKIE_KEY".equals(tempArray[0])) {
                    return str;
                }
            }
        }

        return "";
    }
}
