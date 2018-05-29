package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.IntegrityPublicity;
import com.huacainfo.ace.fop.vo.IntegrityPublicityQVo;
import com.huacainfo.ace.fop.vo.IntegrityPublicityVo;

public interface IntegrityPublicityDao {

    IntegrityPublicity selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(IntegrityPublicity record);

    int insertSelective(IntegrityPublicity record);

    int updateByPrimaryKey(IntegrityPublicity record);

    int updateByPrimaryKeySelective(IntegrityPublicity record);

    IntegrityPublicityVo selectVoByPrimaryKey(String id);

    List<IntegrityPublicityVo> findList(@Param("condition") IntegrityPublicityQVo condition,
                                        @Param("start") int start,
                                        @Param("limit") int limit,
                                        @Param("orderBy") String orderBy);

    int findCount(@Param("condition") IntegrityPublicityQVo condition);

    int isExit(IntegrityPublicity record);

}