package com.huacainfo.ace.portal.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huacainfo.ace.portal.model.Notice;
import com.huacainfo.ace.portal.vo.NoticeQVo;
import com.huacainfo.ace.portal.vo.NoticeVo;

public interface NoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(String noticeId);
    
    NoticeVo selectVoByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
    List<NoticeVo> findList(@Param("condition") NoticeQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") NoticeQVo condition);
	 int saveOrUpdateNotice(Notice record);
	 int isExitByTitle(@Param("title") String title);
	 int updateForTopByPrimaryKey(@Param("noticeId") String noticeId,@Param("category") String category);
	 int updateForStatusByPrimaryKey(@Param("noticeId") String noticeId,@Param("status") String status,@Param("departmentId") String departmentId);
	 List<Map<String,String>> selectUserIdByDepartmentId(@Param("departmentId") String departmentId);
	 String selectSqlTextByGroupId(@Param("groupId") String groupId);
	 String selectDictNameByCategoryIdAndCode(@Param("categoryId") String categoryId,@Param("code") String code);
	 List<NoticeVo> findListTop(@Param("categoryId") String categoryId,@Param("departmentId") String departmentId);
}