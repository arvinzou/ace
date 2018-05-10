package com.huacainfo.ace.fop.dao;

import com.huacainfo.ace.fop.model.FopAssociation;
import com.huacainfo.ace.fop.vo.FopAssociationQVo;
import com.huacainfo.ace.fop.vo.FopAssociationVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FopAssociationDao {

    int deleteByPrimaryKey(String id);

    int insert(FopAssociation record);

    int insertSelective(FopAssociation record);

    FopAssociation selectByPrimaryKey(String id);

    FopAssociation selectByDepartmentId(String selectByDepartmentId);

    int updateByPrimaryKeySelective(FopAssociation record);

    int updateByPrimaryKey(FopAssociation record);

    FopAssociationVo selectVoByPrimaryKey(String id);

    List<FopAssociationVo> findList(@Param("condition") FopAssociationQVo condition,
                                    @Param("start") int start, @Param("limit") int limit,
                                    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") FopAssociationQVo condition);

    int isExit(FopAssociation record);

}