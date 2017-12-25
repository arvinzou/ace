package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConferenceRes;

import java.util.List;
import java.util.Map;

public interface RvcConferenceResDao {
    int deleteByPrimaryKey(String id);

    int insert(RvcConferenceRes record);

    int insertSelective(RvcConferenceRes record);

    RvcConferenceRes selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConferenceRes record);

    int updateByPrimaryKey(RvcConferenceRes record);

    /**
     * 获取会议资源
     *
     * @condition typeArray 资源类型
     *                 1-文本内容，2-图片，3-文件，4-视频，5-会议纪要,可多选，多状态间","隔开
     * @condition conferenceId   会议ID,rvc_conference.id
     * @return
     */
    List<RvcConferenceRes> getByType(Map<String, Object> condition);
}