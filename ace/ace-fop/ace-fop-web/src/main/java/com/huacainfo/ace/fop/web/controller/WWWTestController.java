package com.huacainfo.ace.fop.web.controller;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.fop.service.FopCallRecordService;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.SysAccountService;
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


    @RequestMapping(value = "/destoryAccount")
    @ResponseBody
    public ResultResponse destoryAccount(String account) throws Exception {
        return sysAccountService.destoryAccount(account);
    }

}
