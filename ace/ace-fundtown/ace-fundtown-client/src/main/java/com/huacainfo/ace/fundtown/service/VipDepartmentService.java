package com.huacainfo.ace.fundtown.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fundtown.model.VipDepartment;
import com.huacainfo.ace.fundtown.model.VipMemberRes;
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
     *               Demo:{"contactEmail":"30123@qq.com","contactMobile":"18570629027","departmentName":"华彩伟业"}
     * @param openId 入驻用户身份识别ID
     * @return 入驻申请结果
     */
    ResultResponse vipApply(String openId, VipDepartmentVo vipVo) throws Exception;

    /**
     * 获取我的vip信息
     *
     * @param openId 调试时使用，调接口时可以不用理会此参数
     * @return
     */
    ResultResponse getMyVipInfo(String openId);

    /**
     * 通过手机号码获取部门信息
     *
     * @param mobile portal.department.contact_mobile
     * @return
     */
    VipDepartment findByMobile(String mobile);

    /**
     * 获取我的视频信息
     *
     * @param deptId
     * @return
     */
    ResultResponse getMyVideo(String deptId);

    /**
     * 获取我的流程处理信息
     *
     * @param deptId
     * @return
     */
    ResultResponse getMyProcess(String deptId);

    /**
     * vip会员资源文件上传
     *
     * @param deptId   企业ID
     * @param fileName 文件名称
     * @param fileSize 文件大小
     * @param fileUrl  文件资源地址
     */
    VipMemberRes insertVipMemberRes(String deptId, String fileName, long fileSize, String fileUrl);
}
