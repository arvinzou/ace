package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;

import java.util.Map;
import java.util.List;

/**
 * @author: 王恩
 * @version: 2019-03-05
 * @Description: TODO(测试答案管理)
 */
public interface TopicRstService {

    MessageResponse insertTopicRstList(List<TopicRstQVo> listPram, UserProp userProp) throws Exception;

    ResultResponse findTopicFullRstList(String testId) throws Exception;
    ResultResponse updataTopicRstScore(List<Map<String, String>> list,String testId) throws Exception;
}
