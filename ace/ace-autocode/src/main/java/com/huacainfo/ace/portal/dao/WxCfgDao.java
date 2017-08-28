package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.WxCfg;

public interface WxCfgDao {
    int deleteByPrimaryKey(String appId);

    int insert(WxCfg record);

    int insertSelective(WxCfg record);

    WxCfg selectByPrimaryKey(String appId);

    int updateByPrimaryKeySelective(WxCfg record);

    int updateByPrimaryKey(WxCfg record);
}