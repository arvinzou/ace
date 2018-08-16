package com.huacainfo.ace.jxb.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.jxb.model.CourseCmt;
import com.huacainfo.ace.jxb.vo.CourseCmtQVo;
import com.huacainfo.ace.jxb.vo.CourseCmtVo;

/**
 * @author: Arvin
 * @version: 2018-08-06
 * @Description: TODO(课程评论)
 */
public interface CourseCmtService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(课程评论分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CourseCmtVo>
     * @author: Arvin
     * @version: 2018-08-06
     */
    PageResult<CourseCmtVo> findCourseCmtList(CourseCmtQVo condition,
                                              int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertCourseCmt
     * @Description: TODO(添加课程评论)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse insertCourseCmt(CourseCmt obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateCourseCmt
     * @Description: TODO(更新课程评论)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse updateCourseCmt(CourseCmt obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectCourseCmtByPrimaryKey
     * @Description: TODO(获取课程评论)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CourseCmt>
     * @author: Arvin
     * @version: 2018-08-06
     */
    SingleResult<CourseCmtVo> selectCourseCmtByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteCourseCmtByCourseCmtId
     * @Description: TODO(删除课程评论)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-08-06
     */
    MessageResponse deleteCourseCmtByCourseCmtId(String id, UserProp userProp) throws Exception;

    /**
     * 功能描述: 新增课程评论
     *
     * @param courseId 课程id
     * @param userId   评论人id
     * @param content  评论内容
     */
    int addCourseCmt(String courseId, String userId, String content);
}
