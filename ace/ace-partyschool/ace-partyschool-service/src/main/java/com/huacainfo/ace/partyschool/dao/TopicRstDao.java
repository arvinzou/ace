package com.huacainfo.ace.partyschool.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.huacainfo.ace.partyschool.model.TopicOptRst;
import com.huacainfo.ace.partyschool.vo.TopicVo;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.TopicRst;
import com.huacainfo.ace.partyschool.vo.TopicRstQVo;
import com.huacainfo.ace.partyschool.vo.TopicRstVo;

public interface TopicRstDao {

    TopicRst selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TopicRst record);

    int insertTopOptRstList(@Param("list")List<TopicOptRst> list,@Param("tid")String tid);


    int updateByPrimaryKey(TopicRst record);

    int updataTopicRstScore(@Param("id")String id,@Param("youScore") BigDecimal youScore);

    List<TopicVo> findTopicFullRstList(@Param("testId") String testId);

}