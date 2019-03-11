package com.huacainfo.ace.partyschool.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.huacainfo.ace.common.model.PageParamNoChangeSord;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.partyschool.service.TopicRstService;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;
import com.huacainfo.ace.portal.vo.MongoFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/topicRst")
/**
 * @author: 王恩
 * @version: 2019-03-05
 * @Description: TODO(测试答案管理)
 */
public class TopicRstController extends BisBaseController {


    private static final long serialVersionUID = 1L;
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicRstService topicRstService;


    @RequestMapping(value = "/findTopicRstFullList")
    @ResponseBody
    public ResultResponse findTopicRstFullList(String testId) throws Exception {
        return this.topicRstService.findTopicFullRstList(testId);
    }


    @RequestMapping(value = "/updataTopicRstScore")
    @ResponseBody
    public ResultResponse updataTopicRstScore(String jsons) throws Exception {
        JSONObject jsonObject=JSONObject.parseObject(jsons);
        String list=jsonObject.getString("list");
        List<Map<String, String>> listMap = JSON.parseObject(list, new TypeReference<List<Map<String,String>>>(){});
        return this.topicRstService.updataTopicRstScore(listMap,jsonObject.getString("testId"));
    }
}
