package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.EnterpriseProducts;
import com.huacainfo.ace.fop.vo.EnterpriseProductsQVo;
import com.huacainfo.ace.fop.vo.EnterpriseProductsVo;

public interface EnterpriseProductsDao {

    EnterpriseProducts selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(EnterpriseProducts record);

    int insertSelective(EnterpriseProducts record);

    int updateByPrimaryKey(EnterpriseProducts record);

    int updateByPrimaryKeySelective(EnterpriseProducts record);

    EnterpriseProductsVo selectVoByPrimaryKey(String id);

    List
            <EnterpriseProductsVo> findList(@Param("condition") EnterpriseProductsQVo condition,
                                            @Param("start") int start,
                                            @Param("limit") int limit,
                                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EnterpriseProductsQVo condition);

    int isExit(EnterpriseProducts record);

}