package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.CourseCmt;
import com.huacainfo.ace.jxb.vo.CourseCmtQVo;
import com.huacainfo.ace.jxb.vo.CourseCmtVo;

public interface CourseCmtDao {

    CourseCmtVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CourseCmt record);

    int insertSelective(CourseCmt record);

    int updateByPrimaryKey(CourseCmt record);

    int updateByPrimaryKeySelective(CourseCmt record);

    
    List<CourseCmtVo> findList(@Param("condition") CourseCmtQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") CourseCmtQVo condition);

	int isExit(CourseCmt record);

}