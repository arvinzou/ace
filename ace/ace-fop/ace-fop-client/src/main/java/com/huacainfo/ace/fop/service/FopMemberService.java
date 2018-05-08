package com.huacainfo.ace.fop.service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.fop.model.FopMember;
import com.huacainfo.ace.fop.vo.FopMemberQVo;
import com.huacainfo.ace.fop.vo.FopMemberVo;

import java.util.List;

/**
 * @author: Arvin
 * @version: 2018-05-04
 * @Description: TODO(通知公告)
 */
public interface FopMemberService {
    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通知公告分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <FopMemberVo>
     * @author: Arvin
     * @version: 2018-05-04
     */
    PageResult<FopMemberVo> findFopMemberList(FopMemberQVo condition, int start, int limit, String orderBy) throws
            Exception;

    /**
     * @throws
     * @Title:insertFopMember
     * @Description:
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    MessageResponse insertFopMember(FopMember obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:updateFopMember
     * @Description:
     * @param: @param obj
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    MessageResponse updateFopMember(FopMember obj, UserProp userProp) throws Exception;

    /**
     * @throws
     * @Title:selectFopMemberByPrimaryKey
     * @Description:
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopMember>
     * @author: Arvin
     * @version: 2018-05-04
     */
    SingleResult<FopMemberVo> selectFopMemberByPrimaryKey(String id) throws Exception;

    /**
     * @throws
     * @Title:deleteFopMemberByFopMemberId
     * @Description:
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    MessageResponse deleteFopMemberByFopMemberId(String id, UserProp userProp) throws
            Exception;

    /**
     * 分类用户角色
     *
     * @param userId    portal.users.user_id
     * @param userProp
     * @param roleTypes 角色类型 4- 	企业/团体会员, 5-	企业/团体非会员
     * @return
     * @throws Exception
     */
    ResultResponse dispatchRoleRight(String userId, UserProp userProp, String[] roleTypes) throws Exception;

    /**
     * 功能描述: 加入会员审核
     *
     * @param params      会员资料参数
     * @param flowType    审核流程类型
     * @param auditResult 审核结果
     * @param userProp    操作员
     * @return:
     * @auther: Arvin Zou
     * @date: 2018/5/5 12:12
     */
    MessageResponse memberJoinAudit(FopMember params, UserProp userProp) throws Exception;

    /**
     * @param relationType 关联类型  0-企业会员 1-团体会员
     * @param relationId   关联ID
     * @param status       数据状态 1-正常  -1-已删除
     * @return
     * @Author Arvin
     */
    List<FopMember> selectByRelationType(String relationType, String relationId, String[] status);
}
