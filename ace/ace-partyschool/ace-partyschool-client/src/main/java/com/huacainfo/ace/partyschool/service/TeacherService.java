package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Teacher;
import com.huacainfo.ace.partyschool.vo.TeacherQVo;
import com.huacainfo.ace.partyschool.vo.TeacherVo;

/**
 * @author: Arvin
 * @version: 2019-01-02
 * @Description: TODO(教职工管理)
 */
public interface TeacherService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(教职工管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <TeacherVo>
     * @author: Arvin
     * @version: 2019-01-02
     */
    PageResult<TeacherVo> findTeacherList(TeacherQVo condition,
                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertTeacher
     * @Description: TODO(添加教职工管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    MessageResponse insertTeacher(Teacher obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateTeacher
     * @Description: TODO(更新教职工管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    MessageResponse updateTeacher(Teacher obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectTeacherByPrimaryKey
     * @Description: TODO(获取教职工管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Teacher>
     * @author: Arvin
     * @version: 2019-01-02
     */
    SingleResult<TeacherVo> selectTeacherByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteTeacherByTeacherId
     * @Description: TODO(删除教职工管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-02
     */
    MessageResponse deleteTeacherByTeacherId(String id, UserProp userProp) throws Exception;

    /**
     * 判断身份证是否已存在
     *
     * @param idCard 身份证号码
     * @return boolean
     */
    boolean isExistByIdCard(String idCard);
}
