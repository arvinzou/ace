package com.huacainfo.ace.partyschool.dao;

import com.huacainfo.ace.partyschool.model.EvaluationExport;
import com.huacainfo.ace.partyschool.model.EvaluationRst;
import com.huacainfo.ace.partyschool.vo.EvaRstReport;
import com.huacainfo.ace.partyschool.vo.EvaluationRstQVo;
import com.huacainfo.ace.partyschool.vo.EvaluationRstVo;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public interface EvaluationRstDao {

    EvaluationRst selectByPrimaryKey(String id);

    int deleteByPrimaryKey(String id);
    int delectGroup(@Param("c") String c,@Param("u") String u);

    int insert(EvaluationRst record);


    int updateByPrimaryKey(EvaluationRst record);


    EvaluationRstVo selectVoByPrimaryKey(String id);

    List<Map<String, String>> statistics(String classScheduleId);

    List<EvaluationRstVo> findList(@Param("condition") EvaluationRstQVo condition,
                                   @Param("start") int start,
                                   @Param("limit") int limit,
                                   @Param("orderBy") String orderBy);


    List<EvaluationRstVo> findListVo(@Param("condition") EvaluationRstQVo condition,
                                     @Param("start") int start,
                                     @Param("limit") int limit,
                                     @Param("orderBy") String orderBy);

    int findCount(@Param("condition") EvaluationRstQVo condition);

    int findCountVo(@Param("condition") EvaluationRstQVo condition);

    int getDoneSize(@Param("condition") EvaluationRstQVo condition);

    int isExit(EvaluationRst record);

    int updateStatus(EvaluationRst record);

    List<EvaluationExport> exportData(@Param("id") String id);

    /**
     * 报表数据列表
     *
     * @param classId 班级ID
     * @return List
     */
    List<EvaRstReport> findReportList(String classId);

    /**
     * 获取某一课程安排的评分列表
     *
     * @param classScheduleId 课程安排ID
     * @return LinkedList
     */
    LinkedList<Map<String, Object>> findScoreList(String classScheduleId);
}
