package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.AccountFlowRecord;
import com.huacainfo.ace.jxb.vo.AccountFlowRecordQVo;
import com.huacainfo.ace.jxb.vo.AccountFlowRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountFlowRecordDao {
    int deleteByPrimaryKey(String id);

    int insert(AccountFlowRecord record);

    int insertSelective(AccountFlowRecord record);

    AccountFlowRecord selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(AccountFlowRecord record);

    int updateByPrimaryKey(AccountFlowRecord record);

    List<AccountFlowRecordVo> findList(@Param("condition") AccountFlowRecordQVo condition,
                                       @Param("start") int start,
                                       @Param("limit") int limit,
                                       @Param("orderBy") String orderBy);

    int findCount(@Param("condition") AccountFlowRecordQVo condition);
}