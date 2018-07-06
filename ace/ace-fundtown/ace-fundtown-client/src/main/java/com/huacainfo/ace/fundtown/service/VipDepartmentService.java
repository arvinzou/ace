package com.huacainfo.ace.fundtown.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fundtown.model.VipDepartment;
import com.huacainfo.ace.fundtown.vo.VipDepartmentQVo;
import com.huacainfo.ace.fundtown.vo.VipDepartmentVo;

public interface VipDepartmentService {
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
    PageResult<VipDepartmentVo> findDepartmentList(VipDepartmentQVo condition, int start, int limit, String orderBy) throws Exception;

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
    MessageResponse insertDepartment(VipDepartment dept, UserProp userProp) throws Exception;

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
    MessageResponse updateDepartment(VipDepartment dept, UserProp userProp) throws Exception;


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
    SingleResult<VipDepartmentVo> selectDepartmentByPrimaryKey(String departmentId) throws Exception;

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

    MessageResponse delDepartmentByPrimaryKey(String departmentId, UserProp userProp) throws Exception;

    /**
     * 入驻申请
     *
     * @param vipVo  入驻申请数据
     * @param openId 入驻用户身份识别ID
     * @return 入驻申请结果
     */
    ResultResponse vipApply(String openId, VipDepartmentVo vipVo);
}
