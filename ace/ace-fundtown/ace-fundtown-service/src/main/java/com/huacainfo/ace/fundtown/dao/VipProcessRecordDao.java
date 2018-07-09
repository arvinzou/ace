package com.huacainfo.ace.fundtown.dao;

import com.huacainfo.ace.fundtown.model.VipProcessRecord;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordQVo;
import com.huacainfo.ace.fundtown.vo.VipProcessRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VipProcessRecordDao {

    VipProcessRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(VipProcessRecord record);

    int insertSelective(VipProcessRecord record);

    int updateByPrimaryKey(VipProcessRecord record);

    int updateByPrimaryKeySelective(VipProcessRecord record);

    VipProcessRecordVo selectVoByPrimaryKey(String id);

    List<VipProcessRecordVo> findList(@Param("condition") VipProcessRecordQVo condition,
                                      @Param("start") int start,
                                      @Param("limit") int limit,
                                      @Param("orderBy") String orderBy);

    int findCount(@Param("condition") VipProcessRecordQVo condition);

    int isExit(VipProcessRecord record);

    List<VipProcessRecordVo> findByDeptId(String deptId);
}