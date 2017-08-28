package com.huacainfo.ace.gesp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.gesp.model.MemberInfo;
import com.huacainfo.ace.gesp.vo.MemberInfoQVo;
import com.huacainfo.ace.gesp.vo.MemberInfoVo;

public interface MemberInfoDao {
	int deleteByPrimaryKey(String id);

	int insert(MemberInfo record);
	
	int isExit(String memberCode);
	int isExitMemberNo(@Param("memberNo") String memberNo,@Param("ownerId") String ownerId,@Param("id") String id);

	MemberInfoVo selectByPrimaryKey(String id);


	int updateByPrimaryKey(MemberInfo record);
	
	int updateMemberBaseByPrimaryKey(MemberInfo record);
	
	List<MemberInfoVo> findList(@Param("condition") MemberInfoQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MemberInfoQVo condition);
	
	MemberInfo selectByMemberCode(String memberCode);

	void updateState(MemberInfo o);

	int isExitMemberLevelId(@Param("memberLevel")String memberLevel, @Param("memberRemark")String memberRemark);
	

}