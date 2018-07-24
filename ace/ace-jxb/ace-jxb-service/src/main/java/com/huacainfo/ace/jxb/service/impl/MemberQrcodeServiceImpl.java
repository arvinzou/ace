package com.huacainfo.ace.jxb.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.jxb.dao.MemberQrcodeDao;
import com.huacainfo.ace.jxb.model.MemberQrcode;
import com.huacainfo.ace.jxb.service.MemberQrcodeService;
import com.huacainfo.ace.jxb.vo.MemberQrcodeQVo;
import com.huacainfo.ace.jxb.vo.MemberQrcodeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("memberQrcodeService")
/**
 * @author: Arvin
 * @version: 2018-07-23
 * @Description: TODO(会员二维码表)
 */
public class MemberQrcodeServiceImpl implements MemberQrcodeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MemberQrcodeDao memberQrcodeDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员二维码表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <MemberQrcodeVo>
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public PageResult<MemberQrcodeVo> findMemberQrcodeList(MemberQrcodeQVo condition, int start,
                                                           int limit, String orderBy) throws Exception {
        PageResult<MemberQrcodeVo> rst = new PageResult<>();
        List<MemberQrcodeVo> list = this.memberQrcodeDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.memberQrcodeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMemberQrcode
     * @Description: TODO(添加会员二维码表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse insertMemberQrcode(MemberQrcode o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户id不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeType())) {
            return new MessageResponse(1, "二维码类型（0-临时二维码 1-永久二维码）不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeUrl())) {
            return new MessageResponse(1, "二维码地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.memberQrcodeDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "会员二维码表名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
//        o.setCreateUserName(userProp.getName());
//        o.setCreateUserId(userProp.getUserId());
        this.memberQrcodeDao.insertSelective(o);
        this.dataBaseLogService.log("添加会员二维码表", "会员二维码表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加会员二维码表完成！");
    }

    /**
     * @throws
     * @Title:updateMemberQrcode
     * @Description: TODO(更新会员二维码表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse updateMemberQrcode(MemberQrcode o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getUserId())) {
            return new MessageResponse(1, "用户id不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeType())) {
            return new MessageResponse(1, "二维码类型（0-临时二维码 1-永久二维码）不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcodeUrl())) {
            return new MessageResponse(1, "二维码地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


//        o.setLastModifyDate(new Date());
//        o.setLastModifyUserName(userProp.getName());
//        o.setLastModifyUserId(userProp.getUserId());
        this.memberQrcodeDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更会员二维码表", "会员二维码表", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更会员二维码表完成！");
    }

    /**
     * @throws
     * @Title:selectMemberQrcodeByPrimaryKey
     * @Description: TODO(获取会员二维码表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MemberQrcode>
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public SingleResult<MemberQrcodeVo> selectMemberQrcodeByPrimaryKey(String id) throws Exception {
        SingleResult
                <MemberQrcodeVo> rst = new SingleResult<>();
        rst.setValue(this.memberQrcodeDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMemberQrcodeByMemberQrcodeId
     * @Description: TODO(删除会员二维码表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-07-23
     */
    @Override
    public MessageResponse deleteMemberQrcodeByMemberQrcodeId(String id, UserProp userProp) throws
            Exception {
        this.memberQrcodeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除会员二维码表", "会员二维码表",
                String.valueOf(id),
                String.valueOf(id), "会员二维码表", userProp);
        return new MessageResponse(0, "会员二维码表删除完成！");
    }

}
