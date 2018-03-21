package com.huacainfo.ace.iop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvTaskUsers;
import com.huacainfo.ace.iop.vo.EvTaskUsersQVo;
import com.huacainfo.ace.iop.vo.EvTaskUsersVo;

public interface EvTaskUsersMapper {
	
    int deleteByPrimaryKey(String EvTaskUsersId);

    int insert(EvTaskUsers record);

    int insertSelective(EvTaskUsers record);

    EvTaskUsersVo selectByPrimaryKey(String EvTaskUsersId);

    int updateByPrimaryKeySelective(EvTaskUsers record);
    int updateVoteByPrimaryKey(EvTaskUsers record);

    int updateByPrimaryKey(EvTaskUsers record);
    
    List<EvTaskUsersVo> findList(@Param("condition") EvTaskUsersQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") EvTaskUsersQVo condition);

	int isExit(EvTaskUsers record);
	
	List<Map<String,Object>> selectUserListByDeptId(@Param("evTaskId") String evTaskId,@Param("id") String id,@Param("limit") int limit);
	
	int updateForReset(@Param("id") String id,@Param("evTaskId") String evTaskId,@Param("userId") String userId);
	
	List<Map<String,Object>> selectPrintUserListByDeptId(@Param("evTaskId") String evTaskId,@Param("id") String id,@Param("limit") int limit);
	
}