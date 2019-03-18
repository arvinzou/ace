package com.huacainfo.ace.common.tools;

import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class URLKit {

    public static final String UTF_8 = "utf-8";

    private URLKit() {

    }

    /**
     * 字符串URL解码
     *
     * @param str     字符串
     * @param charset 字符编码
     * @return String
     */
    public static String strDecoder(String str, String charset) {
        try {
            return URLDecoder.decode(str, charset);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }


    /**
     * 字符串URL编码
     *
     * @param str     字符串
     * @param charset 字符编码
     * @return String
     */
    public static String strEncoder(String str, String charset) {
        try {
            return URLEncoder.encode(str, charset);
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * 将url参数转换成map
     *
     * @param param aa=11&bb=22&cc=33
     * @return Map<String, String>
     */
    public static Map<String, String> strToMap(String param) {
        Map<String, String> map = new HashMap<String, String>();
        if (StringUtils.isBlank(param)) {
            return map;
        }
        String[] params = param.split("&");
        String key = "";
        String value = "";
        for (String temp : params) {
            key = temp.substring(0, temp.indexOf("="));
            value = temp.substring(temp.indexOf("=") + 1, temp.length());
            map.put(key, value);
        }

        return map;
    }

    /**
     * deCoder Map的value值
     *
     * @param origin 返回URL解码后的集合
     * @return Map<String, String>
     */
    public static Map<String, String> decodeMap(Map<String, String> origin) {
        Map<String, String> result = new HashMap<>();

        Iterator<Map.Entry<String, String>> entries = origin.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, String> entry = entries.next();
            result.put(entry.getKey(), strDecoder(entry.getValue(), UTF_8));
        }

        return result;
    }

    /**
     * 将map转换成url
     *
     * @param map 需处理的map集合
     * @return String
     */
    public static String mapToStr(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append(entry.getKey() + "=" + entry.getValue());
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = StringUtils.substringBeforeLast(s, "&");
        }
        return s;
    }

    /**
     * 将map转换为请求字符串
     * <p>data=xxx&msg_type=xxx</p>
     *
     * @param params  参数列表
     * @param charset 字符编码
     * @return String
     */
    public static String encodeStrToMap(Map<String, Object> params, String charset) {
        if (params == null || params.isEmpty()) {
            return null;
        }

        StringBuffer data = new StringBuffer();
        boolean flag = false;

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (flag) {
                data.append("&");
            } else {
                flag = true;
            }
            data.append(entry.getKey()).append("=").append(strEncoder(entry.getValue().toString(), charset));
        }

        return data.toString();

    }
}
