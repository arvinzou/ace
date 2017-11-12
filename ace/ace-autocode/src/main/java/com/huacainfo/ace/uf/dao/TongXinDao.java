package com.huacainfo.ace.uf.dao;

import com.huacainfo.ace.uf.model.TongXin;

public interface TongXinDao {
    int deleteByPrimaryKey(String tongxinId);

    int insert(TongXin record);

    int insertSelective(TongXin record);

    TongXin selectByPrimaryKey(String tongxinId);

    int updateByPrimaryKeySelective(TongXin record);

    int updateByPrimaryKey(TongXin record);
}