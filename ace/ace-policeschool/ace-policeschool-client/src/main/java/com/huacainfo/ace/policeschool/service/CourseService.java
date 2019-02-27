package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.policeschool.model.Course;
import com.huacainfo.ace.policeschool.vo.CourseQVo;
import com.huacainfo.ace.policeschool.vo.CourseVo;

/**
 * @author: 王恩
 * @version: 2019-01-02
 * @Description: TODO(课程管理)
 */
public interface CourseService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseVo>
     * @author: 王恩
     * @version: 2019-01-02
     */
    PageResult<CourseVo> findCourseList(CourseQVo condition, int start, int limit, String orderBy) throws Exception;

    ResultResponse findListClassifiedName(CourseQVo condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCourse
     * @Description: TODO(添加课程管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    MessageResponse insertCourse(Course obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCourse
     * @Description: TODO(更新课程管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    MessageResponse updateCourse(Course obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCourseByPrimaryKey
     * @Description: TODO(获取课程管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Course>
     * @author: 王恩
     * @version: 2019-01-02
     */
    SingleResult
            <CourseVo> selectCourseByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCourseByCourseId
     * @Description: TODO(删除课程管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2019-01-02
     */
    MessageResponse deleteCourseByCourseId(String id, UserProp userProp) throws Exception;

    MessageResponse softdel(String id, UserProp userProp) throws Exception;
}