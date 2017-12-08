package com.huacainfo.ace.rvc.kedapi.common.kits;

import com.huacainfo.ace.common.tools.StringUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Arvin on 2017/11/28.
 */
public class HttpKit {

    private HttpKit() {

    }

    private static Logger logger = Logger.getLogger(HttpKit.class);

    /**
     * 带cookies的post
     *
     * @param urlStr  请求地址
     * @param param   请求参数
     * @param cookies 携带cookies信息，可为空
     * @return Map<String,String>
     * ********  Key     value
     * 正常返回：<resp,请求返回值>
     * *********<cookies,cookies值>
     * 异常返回：<errorCode,-1>
     * *********<errorMsg,errorMsg>
     */
    public static Map<String, String> doPost(String urlStr, String param, String cookies) {
        Map<String, String> rtnMap = new HashMap<>();
        URL url = null;
        HttpURLConnection hc = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection.setFollowRedirects(true);
            hc = (HttpURLConnection) url.openConnection();
            hc.setRequestMethod("POST");
            hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
            hc.setDoOutput(true);
            hc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            hc.setRequestProperty("Content-Language", "zh-cn");
            hc.setRequestProperty("Connection", "keep-alive");
            hc.setRequestProperty("Cache-Control", "no-cache");
            //设置请求 cookies 信息
            if (!StringUtils.isEmpty(cookies)) {
                hc.setRequestProperty("Cookie", cookies);
            }

            //post 请求参数
            DataOutputStream out = new DataOutputStream(hc.getOutputStream());
            out.writeBytes(param);
            out.flush();
            out.close();
            //cookies操作
            //获取返回的 cookies 信息
            Map<String, List<String>> maps = hc.getHeaderFields();
            StringBuffer cookiesSB = new StringBuffer();
            List<String> cookiesList = maps.get("Set-Cookie");
            if (!CollectionUtils.isEmpty(cookiesList)) {
                Iterator<String> it = cookiesList.iterator();
                cookiesSB.append("eos_style_cookie=default; ");
                while (it.hasNext()) {
                    cookiesSB.append(it.next());
                }
            }


            //获取返回信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(hc.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();

            //处理返回值
            rtnMap.put("resp", buffer.toString());
            rtnMap.put("cookies", cookiesSB.toString());
            return rtnMap;
        } catch (IOException e) {
            logger.error(e);
            rtnMap.put("errorCode", "-1");
            rtnMap.put("errorMsg", e.getMessage());
            return rtnMap;
        } finally {
            if (hc != null) {
                hc.disconnect();
            }
        }
    }

    /**
     * 带cookies的 get
     *
     * @param urlStr  请求地址
     * @param param   请求参数
     * @param cookies 携带cookies信息，可为空
     * @return Map<String,String>
     * ********  Key     value
     * 正常返回：<resp,请求返回值>
     * *********<cookies,cookies值>
     * 异常返回：<errorCode,-1>
     * *********<errorMsg,errorMsg>
     */
    public static String doGet(String urlStr, String param, String cookies) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr + "?" + param);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Language", "zh-cn");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.setRequestProperty("Cache-Control", "no-cache");

            connection.setRequestMethod("GET");
            //设置请求 cookies 信息
            if (!StringUtils.isEmpty(cookies)) {
                connection.setRequestProperty("Cookie", cookies);
            }

            //获取返回信息
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();

            //处理返回值
            return buffer.toString();
        } catch (IOException e) {
            logger.error("HttpKit.error--> {}", e);
            return "";
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    /**
     * 通过网络访问json并读取文件
     *
     * @param url:http://127.0.0.1:80/dashboard/dept_uuid.json
     * @return:json文件的内容
     */
    public static String loadJson(String url,String charset) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), charset));//"UTF-8"
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
