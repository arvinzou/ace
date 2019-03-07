package com.huacainfo.ace.partyschool.service.impl;


import java.util.*;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.TestRstDao;
import com.huacainfo.ace.partyschool.dao.TopicDao;
import com.huacainfo.ace.partyschool.model.TestRst;
import com.huacainfo.ace.partyschool.vo.TopicVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.partyschool.dao.TopicRstDao;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.partyschool.service.TopicRstService;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;

@Service("topicRstService")
/**
 * @author: 王恩
 * @version: 2019-03-05
 * @Description: TODO(测试答案管理)
 */
public class TopicRstServiceImpl implements TopicRstService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TopicRstDao topicRstDao;
    @Autowired
    private TestRstDao testRstDao;
    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public MessageResponse insertTopicRstList(List<TopicRstQVo> listPram, UserProp userProp) throws Exception {
        String id = GUIDUtil.getGUID();
        for (TopicRstQVo item : listPram) {
            item.setTestRstId(id);
            String tid=GUIDUtil.getGUID();
            item.setId(tid);
            this.topicRstDao.insert(item);
            if (item.getTopicOptList().size() > 0) {
                this.topicRstDao.insertTopOptRstList(item.getTopicOptList(),tid);
            }
        }
        TestRst testRst = new TestRst();
        testRst.setId(id);
        testRst.setName(listPram.get(0).getTname());
        testRst.settId(listPram.get(0).getTid());
        testRst.setCreateDate(new Date());
        testRst.setCreateUserId(userProp.getUserId());
        testRst.setCreateUserName(userProp.getName());
        this.testRstDao.insert(testRst);
        return new MessageResponse(0, "保存成功！");
    }

    @Override
    public ResultResponse findTopicFullRstList(String testId) throws Exception {
        if(CommonUtils.isBlank(testId)){
            return new ResultResponse(ResultCode.FAIL,"test主键不能为空");
        }

        SqlSession session = this.sqlSession.getSqlSessionFactory().openSession(ExecutorType.REUSE);
        Configuration configuration = session.getConfiguration();
        configuration.setSafeResultHandlerEnabled(false);
        TopicRstDao dao = session.getMapper(TopicRstDao.class);
        List<TopicVo> list=new ArrayList<>();
        try {
            list = dao.findTopicFullRstList(testId);
        } catch (Exception e) {
            session.close();
        } finally {
            session.close();
        }
        return new ResultResponse(ResultCode.SUCCESS,"成功",list);
    }

}
