package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.vo.CourseAuditQVo;
import com.huacainfo.ace.jxb.vo.CourseAuditVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseAuditDao {

    CourseAudit selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CourseAudit record);

    int insertSelective(CourseAudit record);

    int updateByPrimaryKey(CourseAudit record);

    int updateByPrimaryKeySelective(CourseAudit record);

    CourseAuditVo selectVoByPrimaryKey(String id);

    List<CourseAuditVo> findList(@Param("condition") CourseAuditQVo condition,
                                 @Param("start") int start,
                                 @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CourseAuditQVo condition);

    int isExit(CourseAudit record);

}