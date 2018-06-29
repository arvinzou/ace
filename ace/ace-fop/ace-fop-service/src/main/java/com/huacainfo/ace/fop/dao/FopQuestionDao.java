package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.FopQuestion;
import com.huacainfo.ace.fop.vo.FopQuestionQVo;
import com.huacainfo.ace.fop.vo.FopQuestionVo;

public interface FopQuestionDao {

    FopQuestionVo selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(FopQuestion record);

    int insertSelective(FopQuestion record);

    int updateByPrimaryKey(FopQuestion record);

    int updateByPrimaryKeySelective(FopQuestion record);

    FopQuestionVo selectVoByPrimaryKey(String id);

    List<FopQuestionVo> findList(@Param("condition") FopQuestionQVo condition,
                                 @Param("start") int start, @Param("limit") int limit,
                                 @Param("orderBy") String orderBy);

    List<FopQuestionVo> findListVo(@Param("condition") FopQuestionQVo condition,
                                   @Param("start") int start, @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

    List<FopQuestionVo> findCommentList(@Param("parentId") String parentId);

    int findCount(@Param("condition") FopQuestionQVo condition);

    int isExit(FopQuestion record);

}