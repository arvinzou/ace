package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.MemberSignLog;
import com.huacainfo.ace.jxb.vo.MemberSignLogQVo;
import com.huacainfo.ace.jxb.vo.MemberSignLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberSignLogDao {

    MemberSignLog selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(MemberSignLog record);

    int insertSelective(MemberSignLog record);

    int updateByPrimaryKey(MemberSignLog record);

    int updateByPrimaryKeySelective(MemberSignLog record);

    MemberSignLogVo selectVoByPrimaryKey(String id);

    List<MemberSignLogVo> findList(@Param("condition") MemberSignLogQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") MemberSignLogQVo condition);

    int isExit(MemberSignLog record);

    /**
     * 根据当天日期，查找签到记录
     *
     * @param todayDate 只含年月日 demo：2018-08-02
     * @return MemberSignLog
     */
    MemberSignLog findToday(String todayDate);


}