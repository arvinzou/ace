package com.huacainfo.ace.portal.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.AccessToken;
import com.huacainfo.ace.common.web.tools.AccessTokenUtil;
import com.huacainfo.ace.portal.service.WxCfgService;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxiaoke on 2017/8/16.
 */
public class TokenThread implements Runnable {
	public static AccessToken access_token = null;

    Logger logger = LoggerFactory.getLogger(TokenThread.class);

    private WxCfgService wxCfgService;




	public TokenThread(){

		this.wxCfgService=(WxCfgService)SpringUtils.getBean("wxCfgService");
        logger.info("TokenThread start -> {}", new java.util.Date());
    }

	@Override
	public void run() {
		while (true) {
			try {
				// 调用工具类获取access_token(每日最多获取100000次，每次获取的有效期为7200秒)
				List<Map<String,Object>> list=this.wxCfgService.selectAppList();
				for(Map<String,Object> o:list){
					access_token = AccessTokenUtil.getAccessToken((String) o.get("appId"), (String) o.get("appScret"));
					this.wxCfgService.updateAccessToken((String) o.get("appId"),access_token.getAccess_token(),access_token.getExpires_in());
					if (null != access_token) {
						logger.info("accessToken获取成功：{},{}",access_token.getAccess_token(), access_token.getExpires_in());
					}
				}


				if (null != access_token) {
					// 7000秒之后重新进行获取
					Thread.sleep((access_token.getExpires_in() - 500) * 1000);
				} else {
					// 获取失败时，60秒之后尝试重新获取
					Thread.sleep(60 * 1000);
				}
			} catch (InterruptedException e) {
                logger.error("{}",e);
			}
		}
	}

}
