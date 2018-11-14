package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.WithdrawWxLog;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogQVo;
import com.huacainfo.ace.jxb.vo.WithdrawWxLogVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WithdrawWxLogDao {

    WithdrawWxLog selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(WithdrawWxLog record);

    int updateByPrimaryKey(WithdrawWxLog record);

    WithdrawWxLogVo selectVoByPrimaryKey(String id);

    List<WithdrawWxLogVo> findList(@Param("condition") WithdrawWxLogQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    int findCount(@Param("condition") WithdrawWxLogQVo condition);

    int isExit(WithdrawWxLog record);

    int updateStatus(WithdrawWxLog record);

}