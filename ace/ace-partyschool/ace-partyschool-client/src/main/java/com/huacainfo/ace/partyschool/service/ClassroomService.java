package com.huacainfo.ace.partyschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.partyschool.model.Classroom;
import com.huacainfo.ace.partyschool.vo.ClassroomVo;
import com.huacainfo.ace.partyschool.vo.ClassroomQVo;

/**
 * @author: Arvin
 * @version: 2019-01-03
 * @Description: TODO(教室管理)
 */
public interface ClassroomService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(教室管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ClassroomVo>
     * @author: Arvin
     * @version: 2019-01-03
     */
    PageResult
            <ClassroomVo> findClassroomList(ClassroomQVo condition,
                                            int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertClassroom
     * @Description: TODO(添加教室管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    MessageResponse insertClassroom(Classroom obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateClassroom
     * @Description: TODO(更新教室管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    MessageResponse updateClassroom(Classroom obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectClassroomByPrimaryKey
     * @Description: TODO(获取教室管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Classroom>
     * @author: Arvin
     * @version: 2019-01-03
     */
    SingleResult
            <ClassroomVo> selectClassroomByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteClassroomByClassroomId
     * @Description: TODO(删除教室管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    MessageResponse deleteClassroomByClassroomId(String id, UserProp userProp) throws Exception;

}
