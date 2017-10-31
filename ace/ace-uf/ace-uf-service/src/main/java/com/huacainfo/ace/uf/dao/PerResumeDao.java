package com.huacainfo.ace.uf.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.uf.model.PerResume;
import com.huacainfo.ace.uf.vo.PerResumeQVo;
import com.huacainfo.ace.uf.vo.PerResumeVo;

public interface PerResumeDao {
    int deleteByPrimaryKey(String PerResumeId);

    int insert(PerResume record);


    PerResumeVo selectByPrimaryKey(String PerResumeId);


    int updateByPrimaryKey(PerResume record);
    
    List<PerResumeVo> findList(@Param("condition") PerResumeQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") PerResumeQVo condition);

	int isExit(PerResume record);

}