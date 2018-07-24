package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.MemberRelationDao;
import com.huacainfo.ace.jxb.model.MemberRelation;
import com.huacainfo.ace.jxb.service.MemberRelationService;
import com.huacainfo.ace.jxb.vo.MemberRelationQVo;
import com.huacainfo.ace.jxb.vo.MemberRelationVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("memberRelationService")
/**
 * @author: Arvin
 * @version: 2018-07-23
 * @Description: TODO(会员关系表)
 */
public class MemberRelationServiceImpl implements MemberRelationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MemberRelationDao memberRelationDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员关系表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MemberRelationVo>
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public PageResult<MemberRelationVo> findMemberRelationList(MemberRelationQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<MemberRelationVo> rst = new PageResult<>();
        List<MemberRelationVo> list = this.memberRelationDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.memberRelationDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMemberRelation
     * @Description: TODO(添加会员关系表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse insertMemberRelation(MemberRelation o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenid())) {
            return new MessageResponse(1, "本人openid不能为空！");
        }
        if (CommonUtils.isBlank(o.getUnionid())) {
            return new MessageResponse(1, "本人unionid不能为空！");
        }
        if (CommonUtils.isBlank(o.getStudioId())) {
            return new MessageResponse(1, "工作室id不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.memberRelationDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "会员关系表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
//        o.setCreateUserName(userProp.getName());
//        o.setCreateUserId(userProp.getUserId());
        this.memberRelationDao.insertSelective(o);
        this.dataBaseLogService.log("添加会员关系表", "会员关系表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加会员关系表完成！");
    }

    /**
     * @throws
     * @Title:updateMemberRelation
     * @Description: TODO(更新会员关系表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse updateMemberRelation(MemberRelation o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenid())) {
            return new MessageResponse(1, "本人openid不能为空！");
        }
        if (CommonUtils.isBlank(o.getUnionid())) {
            return new MessageResponse(1, "本人unionid不能为空！");
        }
        if (CommonUtils.isBlank(o.getStudioId())) {
            return new MessageResponse(1, "工作室id不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


//        o.setLastModifyDate(new Date());
//        o.setLastModifyUserName(userProp.getName());
//        o.setLastModifyUserId(userProp.getUserId());
        this.memberRelationDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更会员关系表", "会员关系表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更会员关系表完成！");
    }

    /**
     * @throws
     * @Title:selectMemberRelationByPrimaryKey
     * @Description: TODO(获取会员关系表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberRelation>
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public SingleResult<MemberRelationVo> selectMemberRelationByPrimaryKey(String id) throws Exception {
        SingleResult<MemberRelationVo> rst = new SingleResult<>();
        rst.setValue(this.memberRelationDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMemberRelationByMemberRelationId
     * @Description: TODO(删除会员关系表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse deleteMemberRelationByMemberRelationId(String id, UserProp userProp) throws
            Exception {
        this.memberRelationDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除会员关系表", "会员关系表",
                String.valueOf(id),
                String.valueOf(id), "会员关系表", userProp);
        return new MessageResponse(0, "会员关系表删除完成！");
    }

}
