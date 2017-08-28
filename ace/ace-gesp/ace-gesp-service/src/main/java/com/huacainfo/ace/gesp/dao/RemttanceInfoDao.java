package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.gesp.model.RemttanceInfo;
import com.huacainfo.ace.gesp.vo.RemttanceInfoQVo;
import com.huacainfo.ace.gesp.vo.RemttanceInfoVo;

public interface RemttanceInfoDao {
    int deleteByPrimaryKey(String RemttanceInfoId);

    int insert(RemttanceInfo record);


    RemttanceInfo selectByPrimaryKey(String RemttanceInfoId);


    int updateByPrimaryKey(RemttanceInfo record);
    
    List<RemttanceInfoVo> findList(@Param("condition") RemttanceInfoQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") RemttanceInfoQVo condition);

	int isExit(RemttanceInfo record);
	
	List<Map<String,Object>> selectListByDeptId(String deptId);
}