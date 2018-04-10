package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.vo.CourseAuditQVo;
import com.huacainfo.ace.jxb.vo.CourseAuditVo;

public interface CourseAuditDao {

    CourseAuditVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CourseAudit record);

    int insertSelective(CourseAudit record);

    int updateByPrimaryKey(CourseAudit record);

    int updateByPrimaryKeySelective(CourseAudit record);

    
    List<CourseAuditVo> findList(@Param("condition") CourseAuditQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") CourseAuditQVo condition);

	int isExit(CourseAudit record);

}