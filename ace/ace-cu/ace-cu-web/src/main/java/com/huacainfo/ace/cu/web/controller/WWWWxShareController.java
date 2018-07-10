package com.huacainfo.ace.cu.web.controller;

import com.huacainfo.ace.common.plugins.wechat.util.RandomValidateCode;
import com.huacainfo.ace.common.plugins.wechat.util.WeChatPayUtil;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Auther: Arvin
 * @Date: 2018/7/6 10:21
 * @Description:
 */
@RestController
@RequestMapping("/www/wxShare")
public class WWWWxShareController extends CuBaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 获取票据
     *
     * @return
     */
    @RequestMapping("/getTicket")
    public Map<String, Object> getTicket(String url) {

        String ticket = "";//todo //AccessUtil.getJsApiTicket(sysAccount);
        SortedMap<String, Object> map = new TreeMap<>();
        map.put("jsapi_ticket", ticket);
        map.put("url", url);
        map.put("timestamp", DateUtil.getDateTime());
        map.put("noncestr", RandomValidateCode.CreateRadom(32, 2));
        logger.debug("getTicket.sign.param={}", map);
        String sign = WeChatPayUtil.createSign("SHA1", "utf-8", map, null);
        map.put("signature", sign);
        map.put("appId", PropertyUtil.getProperty("appid"));

        map.remove("url");
        map.remove("jsapi_ticket");
        logger.debug("getTicket.result={}", map);
        return map;
    }
}
