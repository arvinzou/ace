package com.huacainfo.ace.portal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.portal.model.QueueCmccWait;
import com.huacainfo.ace.portal.vo.QueueCmccWaitQVo;
import com.huacainfo.ace.portal.vo.QueueCmccWaitVo;

public interface QueueCmccWaitMapper {
    int deleteByPrimaryKey(String queueId);

    int insert(QueueCmccWait record);

    int insertSelective(QueueCmccWait record);

    QueueCmccWait selectByPrimaryKey(String queueId);

    int updateByPrimaryKeySelective(QueueCmccWait record);

    int updateByPrimaryKey(QueueCmccWait record);
    List<QueueCmccWaitVo> findList(@Param("condition") QueueCmccWaitQVo condition,
                                   @Param("start") int start, @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);

	int findCount(@Param("condition") QueueCmccWaitQVo condition);
}