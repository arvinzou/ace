package com.huacainfo.ace.society.web.controller;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.result.ResultResponse;
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
    private KafkaProducerService kafkaProducerService;

    @RequestMapping("/synUserList")
    public ResultResponse synUserList() throws Exception {
        Map<String, Object> data = new HashMap<>();
        data.put("sysId", "society");
        data.put("service", "weChatApiService");
        kafkaProducerService.sendMsg("topic.sys.msg", data);

        return new ResultResponse(ResultCode.SUCCESS, "SUCCESS");
    }
}
