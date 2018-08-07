package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CoursePart;
import com.huacainfo.ace.jxb.vo.CoursePartQVo;
import com.huacainfo.ace.jxb.vo.CoursePartVo;

/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程章节)
 */
public interface CoursePartService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程章节分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CoursePartVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    PageResult<CoursePartVo> findCoursePartList(CoursePartQVo condition,
                                                int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCoursePart
     * @Description: TODO(添加课程章节)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse insertCoursePart(CoursePart obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCoursePart
     * @Description: TODO(更新课程章节)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse updateCoursePart(CoursePart obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCoursePartByPrimaryKey
     * @Description: TODO(获取课程章节)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CoursePart>
     * @author: Arvin
     * @version: 2018-08-06
     */
    SingleResult<CoursePartVo> selectCoursePartByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCoursePartByCoursePartId
     * @Description: TODO(删除课程章节)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse deleteCoursePartByCoursePartId(String id, UserProp userProp) throws Exception;

}
