package com.huacainfo.ace.portal.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.vo.DepartmentVo;

import java.util.List;
import java.util.Map;

public interface DepartmentService {
    /**
     * @throws
     * @Title:findDepartmentList
     * @Description: TODO(机构分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @return
     * @param: @throws Exception
     * @return: PageResult<DepartmentVo>
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:50:36
     */
    public abstract PageResult<DepartmentVo> findDepartmentList(Department condition, int start, int limit, String orderBy) throws Exception;

    /**
     * @throws
     * @Title:insertDepartment
     * @Description: TODO(添加一个机构信息)
     * @param: @param dept
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:51:02
     */
    public abstract MessageResponse insertDepartment(Department dept, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateDepartment
     * @Description: TODO(更新机构信息)
     * @param: @param dept
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:51:21
     */
    public abstract MessageResponse updateDepartment(Department dept, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateDepartmentStautsByPrimaryKey
     * @Description: TODO(更新机构状态)
     * @param: @param departmentId
     * @param: @param struts
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:52:09
     */
    public abstract MessageResponse updateDepartmentStautsByPrimaryKey(String departmentId, String struts, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectDepartmentByPrimaryKey
     * @Description: TODO(获取一个机构信息)
     * @param: @param departmentId
     * @param: @return
     * @param: @throws Exception
     * @return: SingleResult<DepartmentVo>
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:52:42
     */
    public abstract SingleResult<DepartmentVo> selectDepartmentByPrimaryKey(String departmentId) throws Exception;

    /**
     * @throws
     * @Title:selectDepartmentTreeList
     * @Description: TODO(获取机构关系树)
     * @param: @return
     * @param: @throws Exception
     * @return: List<Tree>
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:59:15
     */
    public abstract List<Tree> selectDepartmentTreeList(String id, String syid) throws Exception;

    /**
     * @throws
     * @Title:delDepartmentByPrimaryKey
     * @Description: TODO(删除机构)
     * @param: @param departmentId
     * @param: @param userProp
     * @param: @return
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:59:33
     */

    public abstract MessageResponse delDepartmentByPrimaryKey(String departmentId, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectUsersListByDepartmentId
     * @Description: TODO(查询机构下的用户)
     * @param: @param departmentId
     * @param: @return
     * @param: @throws Exception
     * @return: ListResult<Map<String,String>>
     * @author: chenxiaoke
     * @version: 2016年11月16日 下午3:59:53
     */

    public abstract ListResult<Map<String, String>> selectUsersListByDepartmentId(String departmentId) throws Exception;

    /**
     * @param list
     * @param userProp
     * @return
     * @throws Exception
     * @Description:部门数据导入
     */
    public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception;


    /**
     * 可指定department_id，插入记录
     *
     * @param dept
     * @param userProp
     * @return
     * @throws Exception
     */
    MessageResponse insertDepartmentWithDepId(Department dept, UserProp userProp) throws Exception;


}
