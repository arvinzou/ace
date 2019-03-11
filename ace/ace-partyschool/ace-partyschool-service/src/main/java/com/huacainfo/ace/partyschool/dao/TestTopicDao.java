package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.TestTopic;
import com.huacainfo.ace.partyschool.vo.TestTopicQVo;
import org.apache.ibatis.annotations.Param;

public interface TestTopicDao {
    int insert(TestTopicQVo record);

    int insertSelective(TestTopic record);

    int findCount(@Param("condition") TestTopicQVo condition);

    int setScore(TestTopic condition);

    int changeIndex(@Param("tid1") String tid1,@Param("tid2") String tid2);

    int delTestTopic(@Param("id") String id);

}