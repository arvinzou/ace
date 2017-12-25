package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConference;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RvcConferenceMapper {
    int deleteByPrimaryKey(String id);

    int insert(RvcConference record);

    int insertSelective(RvcConference record);

    RvcConference selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConference record);

    int updateByPrimaryKey(RvcConference record);

    /**
     * 根据条件，获取查询数据总记录条数
     *
     * @param condition 需要筛选什么，加入什么字段属性
     * @return count
     */
    int findCount(Map<String,Object> condition);

    /**
     * 获取会议列表
     *
     * @param statusArray 会议状态数组
     *                    0-未开始，1-进行中，2-已结束
     * @return list
     */
    List<RvcConference> getList(@Param("statusArray") String[] statusArray);


    /**
     * 根据标题查找
     *
     * @param condition 查询条件，带分页功能
     */
    List<RvcConference> search(Map<String, Object> condition);
}