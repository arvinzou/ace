package com.huacainfo.ace.jxb.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.jxb.model.TeacherAudit;
import com.huacainfo.ace.jxb.vo.TeacherAuditQVo;
import com.huacainfo.ace.jxb.vo.TeacherAuditVo;

public interface TeacherAuditDao {

    TeacherAuditVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(TeacherAudit record);

    int insertSelective(TeacherAudit record);

    int updateByPrimaryKey(TeacherAudit record);

    int updateByPrimaryKeySelective(TeacherAudit record);

    
    List<TeacherAuditVo> findList(@Param("condition") TeacherAuditQVo condition,
			@Param("start") int start, @Param("limit") int limit,
			@Param("orderBy") String orderBy);

	int findCount(@Param("condition") TeacherAuditQVo condition);

	int isExit(TeacherAudit record);

}