package com.huacainfo.ace.fop.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.fop.dao.FopMemberDao;
import com.huacainfo.ace.fop.model.FopMember;
import com.huacainfo.ace.fop.service.FopMemberService;
import com.huacainfo.ace.fop.vo.FopMemberQVo;
import com.huacainfo.ace.fop.vo.FopMemberVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("fopMemberService")
/**
 * @author: Arvin
 * @version: 2018-05-04
 * @Description: TODO(通知公告)
 */
public class FopMemberServiceImpl implements FopMemberService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FopMemberDao fopMemberDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

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
     * @Description: TODO(添加通知公告)
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
     * @Description: TODO(更新通知公告)
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
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.fopMemberDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更通知公告", "通知公告", "", "", "", userProp);
        return new MessageResponse(0, "变更通知公告完成！");
    }

    /**
     * @throws
     * @Title:selectFopMemberByPrimaryKey
     * @Description: TODO(获取通知公告)
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
     * @Description: TODO(删除通知公告)
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
        this.dataBaseLogService.log("删除通知公告", "通知公告",
                String.valueOf(id),
                String.valueOf(id), "通知公告", userProp);
        return new MessageResponse(0, "通知公告删除完成！");
    }
}
