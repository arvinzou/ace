package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.WxFormid;

public interface WxFormIdDao {
    int deleteByPrimaryKey(String formId);

    int insert(WxFormid record);


    WxFormid selectByPrimaryKey(String formId);


    int updateByPrimaryKey(WxFormid record);
}