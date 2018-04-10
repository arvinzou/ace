package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.TestPaper;
import com.huacainfo.ace.jxb.vo.TestPaperQVo;
import com.huacainfo.ace.jxb.vo.TestPaperVo;

public interface TestPaperDao {

    TestPaperVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TestPaper record);

    int insertSelective(TestPaper record);

    int updateByPrimaryKey(TestPaper record);

    int updateByPrimaryKeySelective(TestPaper record);

    
    List<TestPaperVo> findList(@Param("condition") TestPaperQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TestPaperQVo condition);

	int isExit(TestPaper record);

}