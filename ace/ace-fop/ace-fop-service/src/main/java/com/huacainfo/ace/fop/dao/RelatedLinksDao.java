package com.huacainfo.ace.fop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.fop.model.RelatedLinks;
import com.huacainfo.ace.fop.vo.RelatedLinksQVo;
import com.huacainfo.ace.fop.vo.RelatedLinksVo;

public interface RelatedLinksDao {

    RelatedLinks selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(RelatedLinks record);

    int insertSelective(RelatedLinks record);

    int updateByPrimaryKey(RelatedLinks record);

    int updateByPrimaryKeySelective(RelatedLinks record);

    RelatedLinksVo selectVoByPrimaryKey(String id);

    List<RelatedLinksVo> findList(@Param("condition") RelatedLinksQVo condition,
                                  @Param("start") int start,
                                  @Param("limit") int limit,
                                  @Param("orderBy") String orderBy);

    List<RelatedLinksVo> findTreeList(@Param("parentId") String parentId);

    int findCount(@Param("condition") RelatedLinksQVo condition);

    int isExit(RelatedLinks record);

}