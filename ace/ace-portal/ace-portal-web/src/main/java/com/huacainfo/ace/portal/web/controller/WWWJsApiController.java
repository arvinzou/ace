package com.huacainfo.ace.portal.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.util.RandomValidateCode;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.plugins.wechat.util.WeChatPayUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.portal.model.WxCfg;
import com.huacainfo.ace.portal.service.WxCfgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @Auther: Arvin
 * @Date: 2018/7/10 15:40
 * @Description:
 */
@RestController
@RequestMapping("/www/wx/jsapi")
public class WWWJsApiController {

    @Autowired
    private WxCfgService wxCfgService;

    /**
     * 获取微信网页开发配置
     *
     * @param sysId 系统id
     * @param url   当前页url地址
     * @return map
     */
    @RequestMapping(value = "/getConfig")
    public ResultResponse getConfig(String sysId, String url) {
        if (!StringUtil.areNotEmpty(sysId, url)) {
            return new ResultResponse(ResultCode.FAIL, "缺少必要参数");
        }
        WxCfg wxCfg = wxCfgService.findBySysId(sysId);
        //失败可能原因：1、授权地址配置失败；2、IP白名单未配置
        if (null == wxCfg || StringUtil.isEmpty(wxCfg.getTicket())) {
            return new ResultResponse(ResultCode.FAIL, "微信配置获取异常");
        }

        SortedMap<String, Object> map = new TreeMap<>();
        map.put("url", url);
        map.put("timestamp", DateUtil.getDateTime());
        map.put("nonceStr", RandomValidateCode.CreateRadom(32, 2));
        map.put("jsapi_ticket", wxCfg.getTicket());

        String sign = WeChatPayUtil.createSign("SHA1", "utf-8", map, null);
        map.put("signature", sign);
        map.put("appId", wxCfg.getAppId());

        //return
        map.remove("url");
        map.remove("jsapi_ticket");
        return new ResultResponse(ResultCode.SUCCESS, "获取成功", map);
    }
}
