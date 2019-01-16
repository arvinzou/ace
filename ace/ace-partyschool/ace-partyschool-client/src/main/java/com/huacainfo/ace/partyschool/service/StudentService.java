package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Student;
import com.huacainfo.ace.partyschool.vo.StudentQVo;
import com.huacainfo.ace.partyschool.vo.StudentVo;

import java.util.List;
import java.util.Map;

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
     * 判断身份证是否已存在
     *
     * @param idCard 身份证号码
     * @return boolean
     */
    boolean isExistByIdCard(String idCard);

    /**
     * 添加学员
     *
     * @param obj         params
     * @param curUserProp operator
     * @return MessageResponse
     */
    MessageResponse addStudent(Student obj, UserProp curUserProp) throws Exception;

    /**
     * 批量导入学员
     *
     * @param list  导入数据
     * @param clsId 班级ID
     * @return MessageResponse
     * @throws Exception
     */
    MessageResponse insertImportXls(List<Map<String, Object>> list, UserProp userProp, String clsId) throws Exception;

    /**
     * 账户恢复
     *
     * @param id did
     * @return MessageResponse
     */
    MessageResponse recover(String id, UserProp curUserProp) throws Exception;
    /**
     *
     *
     * @param userProp
     * @return SingleResult<Map<String, String>>
     */
    SingleResult<Map<String, Object>> getRoleClassId(UserProp userProp);
}
