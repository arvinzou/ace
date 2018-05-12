package com.huacainfo.ace.portal.dao;

import com.huacainfo.ace.portal.model.MessageTemplate;
import com.huacainfo.ace.portal.vo.MessageTemplateQVo;
import com.huacainfo.ace.portal.vo.MessageTemplateVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageTemplateDao {

    int deleteByPrimaryKey(String id);

    int insert(MessageTemplate record);

    int insertSelective(MessageTemplate record);

    MessageTemplate selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(MessageTemplate record);

    int updateByPrimaryKey(MessageTemplate record);

    MessageTemplateVo selectVoByPrimaryKey(String MessageTemplateId);

    List<MessageTemplateVo> findList(@Param("condition") MessageTemplateQVo condition,
                                     @Param("start") int start, @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") MessageTemplateQVo condition);

    int isExit(MessageTemplate record);


    MessageTemplate findByTmplCode(@Param("sysId") String sysId,
                                   @Param("tmplCode") String tmplCode);
}