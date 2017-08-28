package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.WxFormid;

public interface WxFormIdDao {
    int deleteByPrimaryKey(String formId);

    int insert(WxFormid record);

    int insertSelective(WxFormid record);

    WxFormid selectByPrimaryKey(String formId);

    int updateByPrimaryKeySelective(WxFormid record);

    int updateByPrimaryKey(WxFormid record);
}