package com.huacainfo.ace.common.web.tools;
import java.net.*;
import java.io.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chenxiaoke on 2017/8/16.
 */
public class AccessTokenUtil {


	/**
	 * 获取accessToken
	 * 
	 * @param appID
	 *            微信公众号凭证
	 * @param appScret
	 *            微信公众号凭证秘钥
	 * @return
	 */
	public static AccessToken getAccessToken(String appID, String appScret) {
        Logger logger = LoggerFactory.getLogger(AccessTokenUtil.class);
		AccessToken token = new AccessToken();
		// 访问微信服务器
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appID + "&secret="
				+ appScret;
		try {
			URL getUrl = new URL(url);
			HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
			http.setRequestMethod("GET");
			http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] b = new byte[size];
			is.read(b);
			String message = new String(b, "UTF-8");
			logger.info(message);
			JSONObject json = JSON.parseObject(message);
			token.setAccess_token(json.getString("access_token"));
			token.setExpires_in(new Integer(json.getString("expires_in")));
		} catch (Exception e) {
            logger.error("{}",e);
		}
		return token;
	}
}
