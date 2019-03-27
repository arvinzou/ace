package com.huacainfo.ace.partyschool.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.partyschool.dao.TestRstDao;
import com.huacainfo.ace.partyschool.dao.TopicRstDao;
import com.huacainfo.ace.partyschool.model.TestRst;
import com.huacainfo.ace.partyschool.model.TopicOptRst;
import com.huacainfo.ace.partyschool.service.TopicRstService;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;
import com.huacainfo.ace.partyschool.vo.TopicVo;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public MessageResponse insertTopicRstList(List<TopicRstQVo> listPram,String taskId, UserProp userProp) throws Exception {
        String id = GUIDUtil.getGUID();

        TopicOptRst topicOptRst=new TopicOptRst();
        topicOptRst.setAnswer("0");
        topicOptRst.setYouAnswer("1");
        for (TopicRstQVo item : listPram) {
            String tid=GUIDUtil.getGUID();
            item.setTestRstId(id);
            topicOptRst.setTopicId(tid);
            item.setId(tid);
            this.topicRstDao.insert(item);
            if (item.getTopicOptList().size() > 0) {
                this.topicRstDao.insertTopOptRstList(item.getTopicOptList(),tid);
            }else{
                topicOptRst.setId(GUIDUtil.getGUID());
                topicOptRst.setContent(item.getAnswer());
                this.topicRstDao.insertTopOptRst(topicOptRst);
            }
        }
        TestRst testRst = new TestRst();
        testRst.setId(id);
        testRst.setStatus("1");
        testRst.setTaskId(taskId);
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

    @Override
    public ResultResponse updataTopicRstScore(List<Map<String, String>> list, String testId) throws Exception {
        for(int i=0;i<list.size();i++){
            Map<String, String> item =list.get(i);
            String id=item.get("id");
            String youScore=item.get("youScore");
            if (CommonUtils.isBlank(id)){
                return new ResultResponse(ResultCode.FAIL,"主键丢失。");
            }
            if (CommonUtils.isBlank(youScore)){
                return new ResultResponse(ResultCode.FAIL,"分值丢失。");
            }
            this.topicRstDao.updataTopicRstScore(id,new BigDecimal(youScore));
        }
        testRstDao.computScore(testId);
        return new ResultResponse(ResultCode.SUCCESS,"成功");
    }
}
