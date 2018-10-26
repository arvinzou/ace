package com.huacainfo.ace.society.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.vo.ActivityQVo;
import com.huacainfo.ace.society.vo.ActivityVo;

public interface ActivityDao {

    Activity selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Activity record);

    int insertSelective(Activity record);

    int updateByPrimaryKey(Activity record);

    int updateByPrimaryKeySelective(Activity record);

    ActivityVo selectVoByPrimaryKey(String id);

    ActivityVo selectVoByPrimaryKeyVo(String id);

    List<ActivityVo> findList(@Param("condition") ActivityQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    List<ActivityVo> findUserList(@Param("condition") ActivityQVo condition,
                              @Param("start") int start,
                              @Param("limit") int limit,
                              @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ActivityQVo condition);

    int findUserCount(@Param("condition") ActivityQVo condition);

    int isExit(Activity record);

}