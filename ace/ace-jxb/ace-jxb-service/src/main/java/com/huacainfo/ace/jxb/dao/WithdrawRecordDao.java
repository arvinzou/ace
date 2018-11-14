package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.WithdrawRecord;
import com.huacainfo.ace.jxb.vo.WithdrawRecordQVo;
import com.huacainfo.ace.jxb.vo.WithdrawRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WithdrawRecordDao {

    WithdrawRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(WithdrawRecord record);

    int updateByPrimaryKey(WithdrawRecord record);

    WithdrawRecordVo selectVoByPrimaryKey(String id);

    List<WithdrawRecordVo> findList(@Param("condition") WithdrawRecordQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") WithdrawRecordQVo condition);

    int updateAuditRst(WithdrawRecord record);

}