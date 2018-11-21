package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseSource;
import com.huacainfo.ace.jxb.vo.CourseSourceQVo;
import com.huacainfo.ace.jxb.vo.CourseSourceVo;

/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程资源)
 */
public interface CourseSourceService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程资源分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseSourceVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    PageResult<CourseSourceVo> findCourseSourceList(CourseSourceQVo condition,
                                                    int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCourseSource
     * @Description: TODO(添加课程资源)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse insertCourseSource(CourseSource obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCourseSource
     * @Description: TODO(更新课程资源)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse updateCourseSource(CourseSource obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCourseSourceByPrimaryKey
     * @Description: TODO(获取课程资源)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseSource>
     * @author: Arvin
     * @version: 2018-08-06
     */
    SingleResult<CourseSourceVo> selectCourseSourceByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCourseSourceByCourseSourceId
     * @Description: TODO(删除课程资源)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse deleteCourseSourceByCourseSourceId(String id, UserProp userProp) throws Exception;

}
