package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.society.service.AuditNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Arvin
 * @Date: 2018/11/22 17:07
 * @Description:
 */
@RestController
@RequestMapping("/www/util")
public class WUtilController {
    @Autowired
    private AuditNoticeService auditNoticeService;
    @Autowired
    private KafkaProducerService kafkaProducerService;

    @RequestMapping("/synUserList")
    public ResultResponse synUserList() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("sysId", "society");
        data.put("service", "weChatApiService");
        kafkaProducerService.sendMsg("topic.sys.msg", data);

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS");
    }

    @RequestMapping("/sendToAdmin")
    public ResultResponse sendToAdmin(String content) throws Exception {
        if (StringUtil.isEmpty(content)) {
            return new ResultResponse(ResultCode.FAIL, "输入参数有误");
        }

        return auditNoticeService.sendToAdmin(content);
    }

    @RequestMapping("/sendToCustomer")
    public ResultResponse sendToCustomer(String userId, String content) throws Exception {
        if (!StringUtil.areNotEmpty(userId, content)) {
            return new ResultResponse(ResultCode.FAIL, "输入参数有误");
        }

        return auditNoticeService.sendToCustomer(userId, content);
    }
}
