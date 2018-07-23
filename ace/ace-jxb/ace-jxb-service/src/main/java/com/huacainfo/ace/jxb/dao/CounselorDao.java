package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.Counselor;
import com.huacainfo.ace.jxb.vo.CounselorQVo;
import com.huacainfo.ace.jxb.vo.CounselorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CounselorDao {

    Counselor selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Counselor record);

    int insertSelective(Counselor record);

    int updateByPrimaryKey(Counselor record);

    int updateByPrimaryKeySelective(Counselor record);

    CounselorVo selectVoByPrimaryKey(String id);

    List<CounselorVo> findList(@Param("condition") CounselorQVo condition,
                               @Param("start") int start,
                               @Param("limit") int limit,
                               @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CounselorQVo condition);

    int isExit(Counselor record);

}