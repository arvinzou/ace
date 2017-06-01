package com.huacainfo.ace.luohua.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.luohua.model.Writer;
import com.huacainfo.ace.luohua.vo.WriterQVo;
import com.huacainfo.ace.luohua.vo.WriterVo;

public interface WriterDao {
    int deleteByPrimaryKey(String WriterId);

    int insert(Writer record);


    Writer selectByPrimaryKey(String WriterId);


    int updateByPrimaryKey(Writer record);
    
    List<WriterVo> findList(@Param("condition") WriterQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") WriterQVo condition);

	int isExit(Writer record);


    List<Map<String, String>> selectAuthor(
            @Param("params") Map<String, Object> params);
}