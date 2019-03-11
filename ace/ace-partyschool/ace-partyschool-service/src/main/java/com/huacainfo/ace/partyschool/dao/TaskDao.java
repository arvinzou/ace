package com.huacainfo.ace.partyschool.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.partyschool.model.Task;
import com.huacainfo.ace.partyschool.vo.TaskQVo;
import com.huacainfo.ace.partyschool.vo.TaskVo;

public interface TaskDao {

Task selectByPrimaryKey(String id);

int deleteByPrimaryKey(String id);

int insert(Task record);


int updateByPrimaryKey(Task record);


TaskVo selectVoByPrimaryKey(String id);

List<TaskVo> findList(@Param("condition") TaskQVo condition,
    @Param("start") int start,
    @Param("limit") int limit,
    @Param("orderBy") String orderBy);

    int findCount(@Param("condition") TaskQVo condition);

    int isExit(Task record);

    int updateStatus(@Param("id") String id,@Param("status") String status);


    List<Map<String,Object>> getList(@Param("p")Map<String,Object> p);


    List<Map<String, Object>> getListByCondition(@Param("params") Map<String, Object> params);


    int deleteByPrimaryKeys(@Param("ids") String[] ids);

 }