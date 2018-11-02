package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.Activity;
import com.huacainfo.ace.society.vo.ActivityQVo;
import com.huacainfo.ace.society.vo.ActivityVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /***
     * 获取待关闭活动
     * @return List
     */
    List<Activity> findCloseList();

    /**
     * 自动关闭超时活动数据
     *
     * @return int
     */
    int autoCloseTimeOutData();
}