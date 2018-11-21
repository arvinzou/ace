package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.InformationService;
import com.huacainfo.ace.fop.vo.InformationServiceQVo;
import com.huacainfo.ace.fop.vo.InformationServiceVo;

public interface InformationServiceDao {

    InformationService selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(InformationService record);

    int insertSelective(InformationService record);

    int updateByPrimaryKey(InformationService record);

    int updateByPrimaryKeySelective(InformationService record);

    InformationServiceVo selectVoByPrimaryKey(String id);

    List<InformationServiceVo> findList(@Param("condition") InformationServiceQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") InformationServiceQVo condition);

    int isExit(InformationService record);

}