package com.huacainfo.ace.society.dao;

import com.huacainfo.ace.society.model.CommodityAnnex;
import com.huacainfo.ace.society.vo.CommodityAnnexQVo;
import com.huacainfo.ace.society.vo.CommodityAnnexVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityAnnexDao {

    CommodityAnnex selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(CommodityAnnex record);

    int insertSelective(CommodityAnnex record);

    int updateByPrimaryKey(CommodityAnnex record);

    int updateByPrimaryKeySelective(CommodityAnnex record);

    CommodityAnnexVo selectVoByPrimaryKey(String id);

    List
            <CommodityAnnexVo> findList(@Param("condition") CommodityAnnexQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") CommodityAnnexQVo condition);

    int isExit(CommodityAnnex record);

}