package com.huacainfo.ace.cu.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.cu.dao.CuUserDao;
import com.huacainfo.ace.cu.model.CuUser;
import com.huacainfo.ace.cu.service.CuUserService;
import com.huacainfo.ace.cu.vo.CuUserQVo;
import com.huacainfo.ace.cu.vo.CuUserVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("cuUserService")
/**
 * @author: Arvin
 * @version: 2018-06-13
 * @Description: TODO(会员资料)
 */
public class CuUserServiceImpl implements CuUserService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CuUserDao cuUserDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(会员资料分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult
     * <CuUserVo>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public PageResult<CuUserVo> findCuUserList(CuUserQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<CuUserVo> rst = new PageResult<>();
        List<CuUserVo> list = this.cuUserDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.cuUserDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertCuUser
     * @Description: TODO(添加会员资料)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse insertCuUser(CuUser o, UserProp userProp) throws Exception {

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "微信openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "真实姓名不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }


        int temp = this.cuUserDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "会员资料名称重复！");
        }

        o.setId(GUIDUtil.getGUID());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.cuUserDao.insertSelective(o);
        this.dataBaseLogService.log("添加会员资料", "会员资料", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "添加会员资料完成！");
    }

    /**
     * @throws
     * @Title:updateCuUser
     * @Description: TODO(更新会员资料)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse updateCuUser(CuUser o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getOpenId())) {
            return new MessageResponse(1, "微信openId不能为空！");
        }
        if (CommonUtils.isBlank(o.getRealName())) {
            return new MessageResponse(1, "真实姓名不能为空！");
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
        this.cuUserDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更会员资料", "会员资料", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "变更会员资料完成！");
    }

    /**
     * @throws
     * @Title:selectCuUserByPrimaryKey
     * @Description: TODO(获取会员资料)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<CuUser>
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public SingleResult<CuUserVo> selectCuUserByPrimaryKey(String id) throws Exception {
        SingleResult<CuUserVo> rst = new SingleResult<>();
        rst.setValue(this.cuUserDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteCuUserByCuUserId
     * @Description: TODO(删除会员资料)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-06-13
     */
    @Override
    public MessageResponse deleteCuUserByCuUserId(String id, UserProp userProp) throws Exception {
        this.cuUserDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除会员资料", "会员资料",
                String.valueOf(id),
                String.valueOf(id), "会员资料", userProp);
        return new MessageResponse(0, "会员资料删除完成！");
    }

    /**
     * 查询会员信息
     *
     * @param openId 微信openid
     * @return
     * @throws Exception
     */
    @Override
    public CuUserVo findByOpenId(String openId) {
        return cuUserDao.findByOpenId(openId);
    }

}
