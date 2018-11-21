package com.huacainfo.ace.fop.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.plugins.wechat.constant.ApiURL;
import com.huacainfo.ace.common.plugins.wechat.util.HttpKit;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.fop.service.FopCallRecordService;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.SysAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/6/30 11:53
 * @Description:
 */
@Controller
@RequestMapping("/www/test")
public class WWWTestController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private FopFlowRecordService fopFlowRecordService;
    @Autowired
    private FopCallRecordService fopCallRecordService;
    @Autowired
    private SysAccountService sysAccountService;

    @RequestMapping(value = "/send1")
    @ResponseBody
    public MessageResponse test(String id) throws Exception {
        return fopFlowRecordService.test(id);
    }


    @RequestMapping(value = "/test2")
    @ResponseBody
    public MessageResponse test2(String recordId, String sysId) throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("recordId", recordId);
        data.put("sysId", sysId);
        return fopCallRecordService.test2(data);
    }


    @RequestMapping(value = "/destroyOne")
    @ResponseBody
    public ResultResponse destoryAccount(String account) throws Exception {
        return sysAccountService.destoryAccount(account);
    }


    @RequestMapping(value = "/accessToken")
    @ResponseBody
    public ResultResponse accessToken(String appid, String appSecret) throws Exception {
        logger.debug("1:" + appid + "||" + appSecret);
        logger.debug("2:" + ApiURL.ACCESS_TOKEN_API_URL);

        String url = ApiURL.ACCESS_TOKEN_API_URL.replace("#APPID#", appid).replace("#APPSECRET#", appSecret);
        String respStr = HttpKit.get(url);

        return new ResultResponse(ResultCode.SUCCESS, "获取accessToken", respStr);
    }


    @RequestMapping(value = "/destroyAll")
    @ResponseBody
    public ResultResponse destroyAll() throws Exception {

        return sysAccountService.destroyAll();
    }

    @RequestMapping(value = "/dataImport")
    @ResponseBody
    public ResultResponse dataImport() throws Exception {

        return sysAccountService.dataImport();
    }
}
