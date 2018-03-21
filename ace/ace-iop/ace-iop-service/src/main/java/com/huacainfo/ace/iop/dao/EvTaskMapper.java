package com.huacainfo.ace.iop.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import com.huacainfo.ace.iop.model.EvTask;
import com.huacainfo.ace.iop.vo.EvTaskQVo;
import com.huacainfo.ace.iop.vo.EvTaskVo;

public interface EvTaskMapper {
    int deleteByPrimaryKey(String evTaskId);

    int insert(EvTask record);

    int insertSelective(EvTask record);

    EvTaskVo selectByPrimaryKey(String evTaskId);

    int updateByPrimaryKeySelective(EvTask record);

    int updateByPrimaryKey(EvTask record);

    List<EvTaskVo> findList(@Param("condition") EvTaskQVo condition,
                            @Param("start") int start, @Param("limit") int limit,
                            @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EvTaskQVo condition);

    int isExit(EvTask record);

    List<EvTaskVo> findMyTaskList(@Param("condition") EvTaskQVo condition);

    List<Map<String, Object>> selectTaskTjById(String id);

    List<Map<String, Object>> selectTaskTjTeacherById(String id);

    List<Map<String, Object>> selectVoteUserListByTaskId(String id);

    List<Map<String, Object>> selectTargetListByTemleteId(String id);

    List<Map<String, Object>> selectTargetTopNameByTemleteId(String id);

    List<Map<String, Object>> selectVoteUserListBySetting(@Param("p") Map<String, Object> p);

    List<Map<String, Object>> selectVoteTeacherListByQr(@Param("p") Map<String, Object> p);

    List<Map<String, Object>> selectVoteClassesListByDeptId(@Param("p") Map<String, Object> p);

    Map<String, Object> selectVoteClassesNameById(@Param("classesId") String classesId, @Param("deptId") String deptId, @Param("gradeId") String gradeId);

    List<Map<String, Object>> selectFeedbackById(@Param("takId") String takId, @Param("userId") String userId);

    List<Map<String, Object>> selectFeedbackUserById(@Param("taskId") String takId);

    List<Map<String, Object>> selectZMTeacherBYTaskId(@Param("taskId") String takId,@Param("orderBy") String orderBy);


}