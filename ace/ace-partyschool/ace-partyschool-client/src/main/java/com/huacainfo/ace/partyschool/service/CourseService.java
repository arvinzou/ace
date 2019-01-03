package com.huacainfo.ace.partyschool.service;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Course;
import com.huacainfo.ace.partyschool.vo.CourseVo;
import com.huacainfo.ace.partyschool.vo.CourseQVo;
/**
* @author: 王恩
* @version: 2019-01-02
* @Description:  TODO(课程管理)
*/
public interface CourseService {
/**
*
* @Title:find!{bean.name}List
* @Description:  TODO(课程管理分页查询)
* @param:        @param condition
* @param:        @param start
* @param:        @param limit
* @param:        @param orderBy
* @param:        @throws Exception
* @return:       PageResult
<CourseVo>
    * @throws
    * @author: 王恩
    * @version: 2019-01-02
    */
    PageResult
    <CourseVo> findCourseList(CourseQVo condition,
        int start, int limit, String orderBy) throws Exception;

        /**
        *
        * @Title:insertCourse
        * @Description: TODO(添加课程管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-01-02
        */
        MessageResponse insertCourse(Course obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:updateCourse
        * @Description: TODO(更新课程管理)
        * @param: @param obj
        * @param: @param userProp
        * @param: @throws Exception
        * @return: MessageResponse
        * @throws
        * @author: 王恩
        * @version: 2019-01-02
        */
        MessageResponse updateCourse(Course obj,UserProp userProp) throws Exception;

        /**
        *
        * @Title:selectCourseByPrimaryKey
        * @Description: TODO(获取课程管理)
        * @param: @param id
        * @param: @throws Exception
        * @return: SingleResult<Course>
        * @throws
        * @author: 王恩
        * @version: 2019-01-02
        */
        SingleResult
        <CourseVo> selectCourseByPrimaryKey(String id) throws Exception;

            /**
            *
            * @Title:deleteCourseByCourseId
            * @Description: TODO(删除课程管理)
            * @param: @param id
            * @param: @param userProp
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-01-02
            */
            MessageResponse deleteCourseByCourseId(String id,UserProp userProp) throws Exception;

            /**
            *
            * @Title:audit
            * @Description: TODO(审核课程管理)
            * @param: @param id bean.id
            * @param: @param rst 审核结果 3-通过 4-拒绝
            * @param: @param remark 审核备注
            * @param: @throws Exception
            * @return: MessageResponse
            * @throws
            * @author: 王恩
            * @version: 2019-01-02
            */
//            MessageResponse audit(String id,String rst, String remark, UserProp userProp) throws Exception;
//            }

}