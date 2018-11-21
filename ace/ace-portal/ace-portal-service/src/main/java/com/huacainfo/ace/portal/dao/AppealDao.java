package com.huacainfo.ace.portal.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.Appeal;
import com.huacainfo.ace.portal.vo.AppealQVo;
import com.huacainfo.ace.portal.vo.AppealVo;

public interface AppealDao {
    int deleteByPrimaryKey(String id);

    int insert(Appeal record);


    AppealVo selectByPrimaryKey(String id);


    int updateByPrimaryKey(Appeal record);
    
    List<AppealVo> findList(@Param("condition") AppealQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") AppealQVo condition);

	int isExit(Appeal record);

}