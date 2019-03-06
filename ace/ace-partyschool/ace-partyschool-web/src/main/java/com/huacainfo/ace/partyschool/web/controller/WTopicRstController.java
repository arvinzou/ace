package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.huacainfo.ace.common.result.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.partyschool.service.TopicRstService;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;
import org.springframework.web.multipart.MultipartFile;
import com.huacainfo.ace.portal.vo.MongoFile;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@Controller
@RequestMapping("www/topicRst")
/**
 * @author: 王恩
 * @version: 2019-03-05
 * @Description: TODO(测试答案管理)
 */
public class WTopicRstController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicRstService topicRstService;


    /**
     * @throws
     * @Title:insertTopicRst
     * @Description: TODO(添加测试答案管理)
     * @param: @param jsons
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-03-05
     */
    @RequestMapping(value = "/insertTopicRstList")
    @ResponseBody
    public MessageResponse insertTopicRst(String jsons) throws Exception {
        List<TopicRstQVo> listPram = (List<TopicRstQVo>) JSONArray.parseArray(jsons, TopicRstQVo.class);
        return this.topicRstService.insertTopicRstList(listPram, this.getCurUserProp());
    }

    @RequestMapping(value = "/findTopicRstFullList")
    @ResponseBody
    public ResultResponse findTopicRstFullList(String testId) throws Exception {
        return this.topicRstService.findTopicFullRstList(testId);
    }
}
