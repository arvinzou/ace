package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.ResultResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopMemberDao;
import com.huacainfo.ace.fop.model.FopMember;
import com.huacainfo.ace.fop.service.FopFlowRecordService;
import com.huacainfo.ace.fop.service.FopMemberService;
import com.huacainfo.ace.fop.vo.FopMemberQVo;
import com.huacainfo.ace.fop.vo.FopMemberVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("fopMemberService")
/**
 * @author: Arvin
 * @version: 2018-05-04
 * @Description: 会员)
 */
public class FopMemberServiceImpl implements FopMemberService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopMemberDao fopMemberDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Autowired
    private FopFlowRecordService fopFlowRecordService;

    @Autowired
    private UsersService usersService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: 会员分页查询)
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
    @Override
    public PageResult<FopMemberVo> findFopMemberList(FopMemberQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult
                <FopMemberVo> rst = new PageResult
                <FopMemberVo>();
        List
                <FopMemberVo> list = this.fopMemberDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.fopMemberDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertFopMember
     * @Description: 添加会员)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    @Override
    public MessageResponse insertFopMember(FopMember o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getMermberName())) {
            return new MessageResponse(1, "会员名称不能为空");
        }
        int temp = this.fopMemberDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "会员重复");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.fopMemberDao.insertSelective(o);
        this.dataBaseLogService.log("添加会员成功", "添加会员成功", "", o.getId(), o.getId(), userProp);
        return new MessageResponse(0, "添加会员成功");
    }

    /**
     * @throws
     * @Title:updateFopMember
     * @Description: 更新会员)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    @Override
    public MessageResponse updateFopMember(FopMember o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getMermberName())) {
            return new MessageResponse(1, "会员名称不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopMemberDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更会员", "会员", "", "", "", userProp);
        return new MessageResponse(0, "变更会员完成！");
    }

    /**
     * @throws
     * @Title:selectFopMemberByPrimaryKey
     * @Description: 获取会员)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<FopMember>
     * @author: Arvin
     * @version: 2018-05-04
     */
    @Override
    public SingleResult<FopMemberVo> selectFopMemberByPrimaryKey(String id) throws Exception {
        SingleResult
                <FopMemberVo> rst = new SingleResult
                <FopMemberVo>();
        rst.setValue(this.fopMemberDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteFopMemberByFopMemberId
     * @Description: 删除会员)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-05-04
     */
    @Override
    public MessageResponse deleteFopMemberByFopMemberId(String id,
                                                        UserProp userProp) throws Exception {
        this.fopMemberDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除会员", "会员",
                String.valueOf(id),
                String.valueOf(id), "会员", userProp);
        return new MessageResponse(0, "会员删除完成！");
    }

    /**
     * 分类用户角色
     *
     * @param userId    portal.users.user_id
     * @param userProp
     * @param roleTypes 角色类型 4- 	企业/团体会员, 5-	企业/团体非会员
     * @return
     * @throws Exception
     */
    @Override
    public ResultResponse dispatchRoleRight(String userId, UserProp userProp, String[] roleTypes) throws Exception {
        List<Map<String, Object>> roleList = fopMemberDao.selectRoleList(userProp.getActiveSyId(), roleTypes);
        if (CollectionUtils.isEmpty(roleList)) {
            return new ResultResponse(ResultCode.FAIL, "未预设用户角色，用户赋权失败");
        }
        Map<String, Object> role = roleList.get(0);
        String[] roleId = new String[]{(String) role.get("role_id")};
        MessageResponse rs3 = usersService.insertUsersRole(userId, roleId, userProp);

        return new ResultResponse(rs3);
    }

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
    @Override
    public MessageResponse memberJoinAudit(FopMember params, String flowType, String auditResult, UserProp userProp) throws Exception {

        List<FopMember> memberList = fopMemberDao.selectByRelationType(
                params.getRelationType(),
                params.getRelationId(),
                new String[]{"1"});
        FopMember member;
        if ("0".equals(auditResult)) {
            //
            if (CollectionUtils.isEmpty(memberList)) {
                return insertFopMember(params, userProp);
            } else {
                member = memberList.get(0);
                member.setMemberCode(System.currentTimeMillis() + "");
                member.setMemberLevel("0");
                member.setJoinDate(DateUtil.getNowDate());
                String validDate = DateUtil.getDate("year", DateUtil.getNow(), 1, "");
                member.setValidDate(DateUtil.format(validDate, DateUtil.DEFAULT_DATE_TIME_REGEX));

                return updateFopMember(member, userProp);
            }
        } else {
            if (!CollectionUtils.isEmpty(memberList)) {

            }

            return new MessageResponse(ResultCode.SUCCESS, "审核成功");
        }
    }

    /**
     * @param relationType 关联类型  0-企业会员 1-团体会员
     * @param relationId   关联ID
     * @param status       数据状态 1-正常  -1-已删除
     * @return
     * @Author Arvin
     */
    @Override
    public List<FopMember> selectByRelationType(String relationType, String relationId, String[] status) {
        return fopMemberDao.selectByRelationType(relationType, relationId, status);
    }
}
