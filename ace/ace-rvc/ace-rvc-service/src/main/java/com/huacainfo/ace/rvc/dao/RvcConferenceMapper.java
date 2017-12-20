package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConference;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RvcConferenceMapper {
    int deleteByPrimaryKey(String id);

    int insert(RvcConference record);

    int insertSelective(RvcConference record);

    RvcConference selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConference record);

    int updateByPrimaryKey(RvcConference record);

    /**
     * 获取会议列表
     *
     * @param statusArray 会议状态数组
     *                    0-未开始，1-进行中，2-已结束
     * @return RvcConference
     */
    List<RvcConference> getList(@Param("statusArray") String[] statusArray);
}