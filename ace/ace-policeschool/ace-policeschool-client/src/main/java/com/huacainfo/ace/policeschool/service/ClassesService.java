package com.huacainfo.ace.policeschool.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.*;
import com.huacainfo.ace.policeschool.model.Classes;
import com.huacainfo.ace.policeschool.vo.ClassesQVo;
import com.huacainfo.ace.policeschool.vo.ClassesVo;

import java.util.Map;

/**
 * @author: Arvin
 * @version: 2019-01-03
 * @Description: TODO(班级管理)
 */
public interface ClassesService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(班级管理分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <ClassesVo>
     * @author: Arvin
     * @version: 2019-01-03
     */
    PageResult<ClassesVo> findClassesList(ClassesQVo condition,
                                          int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertClasses
     * @Description: TODO(添加班级管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    MessageResponse insertClasses(Classes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateClasses
     * @Description: TODO(更新班级管理)
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    MessageResponse updateClasses(Classes obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectClassesByPrimaryKey
     * @Description: TODO(获取班级管理)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Classes>
     * @author: Arvin
     * @version: 2019-01-03
     */
    SingleResult<ClassesVo> selectClassesByPrimaryKey(String id) throws Exception;

    ResultResponse selectClassesByPrimaryKeyVo(UserProp userProp, String classId) throws Exception;

    /**
     * @throws
     * @Title:deleteClassesByClassesId
     * @Description: TODO(删除班级管理)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-01-03
     */
    MessageResponse deleteClassesByClassesId(String id, UserProp userProp) throws Exception;

    /**
     * 根据指定条件查询班级信息
     *
     * @param params params
     * @return Map
     */
    Map<String, Object> findByQ(Map<String, Object> params);

    /**
     * 恢复班级信息
     *
     * @param id did
     * @return MessageResponse
     */
    MessageResponse recover(String id, UserProp curUserProp) throws Exception;

    ResultResponse getMyClasses(UserProp userProp) throws Exception;

    ResultResponse getAllClasses(UserProp userProp) throws Exception;

    /**
     * 每天凌晨1点执行
     * 1、关闭时间已到期的班级
     * 2、注销已毕业班级的学员账户信息
     */
    void graduation();

    ListResult<Map<String, Object>> getClassList();
}
