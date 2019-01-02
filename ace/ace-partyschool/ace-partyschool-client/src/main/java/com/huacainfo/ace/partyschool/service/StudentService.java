package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Student;
import com.huacainfo.ace.partyschool.vo.StudentQVo;
import com.huacainfo.ace.partyschool.vo.StudentVo;

/**
 * @author: Arvin
 * @version: 2018-12-29
 * @Description: TODO(学员管理)
 */
public interface StudentService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(学员管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <StudentVo>
     * @author: Arvin
     * @version: 2018-12-29
     */
    PageResult<StudentVo> findStudentList(StudentQVo condition,
                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertStudent
     * @Description: TODO(添加学员管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    MessageResponse insertStudent(Student obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateStudent
     * @Description: TODO(更新学员管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    MessageResponse updateStudent(Student obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectStudentByPrimaryKey
     * @Description: TODO(获取学员管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Student>
     * @author: Arvin
     * @version: 2018-12-29
     */
    SingleResult<StudentVo> selectStudentByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteStudentByStudentId
     * @Description: TODO(删除学员管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    MessageResponse deleteStudentByStudentId(String id, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:audit
     * @Description: TODO(审核学员管理)
     * @param: @param id bean.id
     * @param: @param rst 审核结果 3-通过 4-拒绝
     * @param: @param remark 审核备注
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-12-29
     */
    MessageResponse audit(String id, String rst, String remark, UserProp userProp) throws Exception;
}
