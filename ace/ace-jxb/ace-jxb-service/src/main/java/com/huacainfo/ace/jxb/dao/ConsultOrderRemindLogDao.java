package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.ConsultOrderRemindLog;
import com.huacainfo.ace.jxb.vo.BaseOrderVo;

import java.util.List;

public interface ConsultOrderRemindLogDao {
    int deleteByPrimaryKey(String id);

    int insert(ConsultOrderRemindLog record);

    ConsultOrderRemindLog selectByPrimaryKey(String id);

    int updateByPrimaryKey(ConsultOrderRemindLog record);

    List<BaseOrderVo> findRemindList();

    int isExist(ConsultOrderRemindLog sendLog);
}