package com.huacainfo.ace.rvc.dao;

import com.huacainfo.ace.rvc.model.RvcConferenceAddress;

import java.util.List;

public interface RvcConferenceAddressMapper {
    int deleteByPrimaryKey(String id);

    int insert(RvcConferenceAddress record);

    int insertSelective(RvcConferenceAddress record);

    RvcConferenceAddress selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RvcConferenceAddress record);

    int updateByPrimaryKey(RvcConferenceAddress record);

    /**
     * 获取当前所有视频会议地址
     * @return list
     */
    List<RvcConferenceAddress> getAll();
}