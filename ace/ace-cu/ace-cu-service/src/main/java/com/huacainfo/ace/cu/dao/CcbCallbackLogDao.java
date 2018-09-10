package com.huacainfo.ace.cu.dao;

import com.huacainfo.ace.cu.model.CcbCallbackLog;

public interface CcbCallbackLogDao {
    int deleteByPrimaryKey(String id);

    int insert(CcbCallbackLog record);

    int insertSelective(CcbCallbackLog record);

    CcbCallbackLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CcbCallbackLog record);

    int updateByPrimaryKey(CcbCallbackLog record);
}