package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Tpl;
import com.huacainfo.ace.portal.vo.TplQVo;
import com.huacainfo.ace.portal.vo.TplVo;

public interface TplDao {
    int deleteByPrimaryKey(String TplId);

    int insert(Tpl record);


    TplVo selectByPrimaryKey(String TplId);


    int updateByPrimaryKey(Tpl record);
    
    List<TplVo> findList(@Param("condition") TplQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TplQVo condition);

	int isExit(Tpl record);

    List<Map<String,Object>> getList();

}