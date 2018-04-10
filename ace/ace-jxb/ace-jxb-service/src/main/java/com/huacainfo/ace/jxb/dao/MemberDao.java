package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.Member;
import com.huacainfo.ace.jxb.vo.MemberQVo;
import com.huacainfo.ace.jxb.vo.MemberVo;

public interface MemberDao {

    MemberVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Member record);

    int insertSelective(Member record);

    int updateByPrimaryKey(Member record);

    int updateByPrimaryKeySelective(Member record);

    
    List<MemberVo> findList(@Param("condition") MemberQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") MemberQVo condition);

	int isExit(Member record);

}