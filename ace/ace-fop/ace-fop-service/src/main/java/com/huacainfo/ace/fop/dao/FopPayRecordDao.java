package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopPayRecord;
import com.huacainfo.ace.fop.vo.FopPayRecordQVo;
import com.huacainfo.ace.fop.vo.FopPayRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopPayRecordDao {

    FopPayRecord selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopPayRecord record);

    int insertSelective(FopPayRecord record);

    int updateByPrimaryKey(FopPayRecord record);

    int updateByPrimaryKeySelective(FopPayRecord record);

    FopPayRecordVo selectVoByPrimaryKey(String id);

    List<FopPayRecordVo> findList(@Param("condition") FopPayRecordQVo condition,
                                  @Param("start") int start, @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopPayRecordQVo condition);

    int isExit(FopPayRecord record);

}