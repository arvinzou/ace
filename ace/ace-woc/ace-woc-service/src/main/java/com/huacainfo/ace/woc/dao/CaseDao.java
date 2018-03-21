package com.huacainfo.ace.woc.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.woc.model.Case;
import com.huacainfo.ace.woc.vo.CaseQVo;
import com.huacainfo.ace.woc.vo.CaseVo;

public interface CaseDao {

    CaseVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Case record);

    int insertSelective(Case record);

    int updateByPrimaryKey(Case record);

    int updateByPrimaryKeySelective(Case record);

    
    List<CaseVo> findList(@Param("condition") CaseQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") CaseQVo condition);

	int isExit(Case record);

}