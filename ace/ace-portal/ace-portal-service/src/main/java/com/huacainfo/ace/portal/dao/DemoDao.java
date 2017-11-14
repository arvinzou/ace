package com.huacainfo.ace.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Demo;
import com.huacainfo.ace.portal.vo.DemoQVo;
import com.huacainfo.ace.portal.vo.DemoVo;

public interface DemoDao {
    int deleteByPrimaryKey(String DemoId);

    int insert(Demo record);


    DemoVo selectByPrimaryKey(String DemoId);


    int updateByPrimaryKey(Demo record);
    
    List<DemoVo> findList(@Param("condition") DemoQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") DemoQVo condition);

	int isExit(Demo record);

}