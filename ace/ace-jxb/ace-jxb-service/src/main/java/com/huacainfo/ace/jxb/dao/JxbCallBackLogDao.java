package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.JxbCallBackLog;

public interface JxbCallBackLogDao {
    int deleteByPrimaryKey(String id);

    int insert(JxbCallBackLog record);

    int insertSelective(JxbCallBackLog record);

    JxbCallBackLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(JxbCallBackLog record);

    int updateByPrimaryKey(JxbCallBackLog record);
}