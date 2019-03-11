package com.huacainfo.ace.partyschool.web.controller;

import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.partyschool.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("www/topic")
/**
 * @author: 王恩
 * @version: 2019-02-27
 * @Description: TODO(试题管理)
 */
public class WTestTopicController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicService topicService;


    @RequestMapping(value = "/findTopicFullList")
    @ResponseBody
    public ResultResponse findTopicFullList(String testId,String taskId) throws Exception {
        return this.topicService.findTopicFullList(testId,taskId,this.getCurUserProp());
    }
}
