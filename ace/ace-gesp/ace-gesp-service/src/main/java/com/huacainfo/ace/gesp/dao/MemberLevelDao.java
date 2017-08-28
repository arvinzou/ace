package com.huacainfo.ace.gesp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.MemberLevel;
import com.huacainfo.ace.gesp.vo.MemberLevelQVo;
import com.huacainfo.ace.gesp.vo.MemberLevelVo;

public interface MemberLevelDao {
    int deleteByPrimaryKey(String MemberLevelId);

    int insert(MemberLevel record);


    MemberLevel selectByPrimaryKey(String MemberLevelId);


    int updateByPrimaryKey(MemberLevel record);
    
    List<MemberLevelVo> findList(@Param("condition") MemberLevelQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MemberLevelQVo condition);

	int isExit(MemberLevel record);
	
	/**
	 * 根据协会编号查询会员级别
	 * 
	 * 引用(收费处理模块)
	 * @param deptId
	 * @return condition
	 */
	List<Map<String,Object>> selectListByDeptId(String deptId);
}