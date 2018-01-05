package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.Userinfo;

public interface UserinfoDao {
    int deleteByPrimaryKey(String unionid);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    Userinfo selectByPrimaryKey(String unionid);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKeyWithBLOBs(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}