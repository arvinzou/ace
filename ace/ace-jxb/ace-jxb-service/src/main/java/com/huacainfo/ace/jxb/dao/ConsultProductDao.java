package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.ConsultProduct;
import com.huacainfo.ace.jxb.vo.ConsultProductQVo;
import com.huacainfo.ace.jxb.vo.ConsultProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultProductDao {

    ConsultProduct selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(ConsultProduct record);

    int insertSelective(ConsultProduct record);

    int updateByPrimaryKey(ConsultProduct record);

    int updateByPrimaryKeySelective(ConsultProduct record);

    ConsultProductVo selectVoByPrimaryKey(String id);

    List<ConsultProductVo> findList(@Param("condition") ConsultProductQVo condition,
                                    @Param("start") int start,
                                    @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ConsultProductQVo condition);

    int isExit(ConsultProduct record);

    List<ConsultProduct> findPList(String counselorId);
}