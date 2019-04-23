package com.huacainfo.ace.glink.dao;

import com.huacainfo.ace.glink.model.SeAreaTask;
import com.huacainfo.ace.glink.vo.SeAreaTaskQVo;
import com.huacainfo.ace.glink.vo.SeAreaTaskVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeAreaTaskDao {

    SeAreaTask selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);

    int insert(SeAreaTask record);

    int updateByPrimaryKey(SeAreaTask record);

    SeAreaTaskVo selectVoByPrimaryKey(String id);

    List<SeAreaTaskVo> findList(@Param("condition") SeAreaTaskQVo condition,
                                @Param("start") int start,
                                @Param("limit") int limit,
                                @Param("orderBy") String orderBy);

    int findCount(@Param("condition") SeAreaTaskQVo condition);

    int isExist(SeAreaTask record);

    int allClear();

    SeAreaTask findByTaskNo(String taskNo);

    int exeTask(@Param("areaNodeID") String areaNodeID,
                @Param("taskNo") int taskNo,
                @Param("exeState") String exeState,
                @Param("exeRst") String exeRst);
}
