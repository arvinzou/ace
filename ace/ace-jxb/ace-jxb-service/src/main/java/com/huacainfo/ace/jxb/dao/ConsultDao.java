package com.huacainfo.ace.jxb.dao;

import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.jxb.vo.ConsultQVo;
import com.huacainfo.ace.jxb.vo.ConsultVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsultDao {

    Consult selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(Consult record);

    int insertSelective(Consult record);

    int updateByPrimaryKey(Consult record);

    int updateByPrimaryKeySelective(Consult record);

    ConsultVo selectVoByPrimaryKey(String id);

    List<ConsultVo> findList(@Param("condition") ConsultQVo condition,
                             @Param("start") int start,
                             @Param("limit") int limit,
                             @Param("orderBy") String orderBy);

    int findCount(@Param("condition") ConsultQVo condition);

    int isExit(Consult record);

}