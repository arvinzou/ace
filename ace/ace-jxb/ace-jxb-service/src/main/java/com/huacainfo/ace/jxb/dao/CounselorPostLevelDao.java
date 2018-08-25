package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.CounselorPostLevel;
import com.huacainfo.ace.jxb.vo.CounselorPostLevelQVo;
import com.huacainfo.ace.jxb.vo.CounselorPostLevelVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CounselorPostLevelDao {
    int deleteByPrimaryKey(String id);

    int insert(CounselorPostLevel record);

    int insertSelective(CounselorPostLevel record);

    CounselorPostLevel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CounselorPostLevel record);

    int updateByPrimaryKey(CounselorPostLevel record);

    CounselorPostLevelVo findByCounselorId(String counselorId);

    List<CounselorPostLevelVo> findList(@Param("condition") CounselorPostLevelQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CounselorPostLevelQVo condition);
}