package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseAudit;
import com.huacainfo.ace.jxb.vo.CourseAuditQVo;
import com.huacainfo.ace.jxb.vo.CourseAuditVo;

/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程审核)
 */
public interface CourseAuditService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程审核分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseAuditVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    PageResult<CourseAuditVo> findCourseAuditList(CourseAuditQVo condition,
                                                  int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCourseAudit
     * @Description: TODO(添加课程审核)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse insertCourseAudit(CourseAudit obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCourseAudit
     * @Description: TODO(更新课程审核)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse updateCourseAudit(CourseAudit obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCourseAuditByPrimaryKey
     * @Description: TODO(获取课程审核)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseAudit>
     * @author: Arvin
     * @version: 2018-08-06
     */
    SingleResult<CourseAuditVo> selectCourseAuditByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCourseAuditByCourseAuditId
     * @Description: TODO(删除课程审核)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse deleteCourseAuditByCourseAuditId(String id, UserProp userProp) throws Exception;

}
