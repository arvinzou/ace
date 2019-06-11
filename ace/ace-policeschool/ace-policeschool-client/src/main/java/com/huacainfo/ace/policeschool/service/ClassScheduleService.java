package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.model.ClassSchedule;
import com.huacainfo.ace.policeschool.vo.ClassScheduleQVo;
import com.huacainfo.ace.policeschool.vo.ClassScheduleVo;

import java.util.List;

/**
 * @author: 王恩
 * @version: 2019-01-06
 * @Description: TODO(课程表管理)
 */
public interface ClassScheduleService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程表管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<ClassScheduleVo>
     * @author: 王恩
     * @version: 2019-01-06
     */
    PageResult<ClassScheduleVo> findClassScheduleList(ClassScheduleQVo condition,
                                                      int start, int limit, String orderBy) throws Exception;

    PageResult<ClassScheduleVo> LearnedCourses(ClassScheduleQVo condition,
                                               int start, int limit, String orderBy) throws Exception;

    ResultResponse MyClassSchedule(ClassScheduleQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception;

    ResultResponse notDoneTestList(ClassScheduleQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception;

    ResultResponse doneTestList(ClassScheduleQVo condition, int start, int limit, String orderBy, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:insertClassSchedule
     * @Description: TODO(添加课程表管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    MessageResponse insertClassSchedule(ClassSchedule obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateClassSchedule
     * @Description: TODO(更新课程表管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    MessageResponse updateClassSchedule(ClassSchedule obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectClassScheduleByPrimaryKey
     * @Description: TODO(获取课程表管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<ClassSchedule>
     * @author: 王恩
     * @version: 2019-01-06
     */
    SingleResult<ClassScheduleVo> selectClassScheduleByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteClassScheduleByClassScheduleId
     * @Description: TODO(删除课程表管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-06
     */
    MessageResponse deleteClassScheduleByClassScheduleId(String id, UserProp userProp) throws Exception;

    /**
     * 获取数据集合
     *
     * @return ResultResponse
     */
    List<ClassScheduleVo> findList(ClassScheduleQVo condition, int start, int limit, String orderBy);

    /**
     * 教官结课处理
     *
     * @param teacherId     教官用户ID
     * @param clsScheduleId 课程ID
     */
    ResultResponse closeClass(String teacherId, String clsScheduleId);

    /**
     * 教官课时统计报表
     *
     * @param teacherId 教官用户ID
     * @return ResultResponse
     */
    ResultResponse classReport(String teacherId);
}
