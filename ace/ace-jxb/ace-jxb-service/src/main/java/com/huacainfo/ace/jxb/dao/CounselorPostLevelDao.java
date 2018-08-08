package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.CounselorPostLevel;
import com.huacainfo.ace.jxb.vo.CounselorPostLevelVo;

public interface CounselorPostLevelDao {
    int deleteByPrimaryKey(String id);

    int insert(CounselorPostLevel record);

    int insertSelective(CounselorPostLevel record);

    CounselorPostLevel selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CounselorPostLevel record);

    int updateByPrimaryKey(CounselorPostLevel record);

    CounselorPostLevelVo findByCounselorId(String counselorId);
}