package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.dao.BlacklistDao;
import com.huacainfo.ace.woc.model.Blacklist;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.BlacklistService;
import com.huacainfo.ace.woc.vo.BlacklistVo;
import com.huacainfo.ace.woc.vo.BlacklistQVo;

@Service("blacklistService")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description: TODO(黑名单档案)
 */
public class BlacklistServiceImpl implements BlacklistService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BlacklistDao blacklistDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(黑名单档案分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<BlacklistVo>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public PageResult<BlacklistVo> findBlacklistList(BlacklistQVo condition, int start,
                                                     int limit, String orderBy) throws Exception {
        PageResult<BlacklistVo> rst = new PageResult<BlacklistVo>();
        List<BlacklistVo> list = this.blacklistDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.blacklistDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertBlacklist
     * @Description: TODO(添加黑名单档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse insertBlacklist(Blacklist o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        o.setLastModifyDate(DateUtil.getNowDate());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getIsBlack())) {
            return new MessageResponse(1, "是否黑名单不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.blacklistDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "黑名单档案名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.blacklistDao.insertSelective(o);
        this.dataBaseLogService.log("添加黑名单档案", "黑名单档案", "", "insertBlacklist",
                "insertBlacklist", userProp);
        return new MessageResponse(0, "添加黑名单档案完成！");
    }

    /**
     * @throws
     * @Title:updateBlacklist
     * @Description: TODO(更新黑名单档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse updateBlacklist(Blacklist o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getIsBlack())) {
            return new MessageResponse(1, "是否黑名单不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.blacklistDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更黑名单档案", "黑名单档案", "", "updateBlacklist",
                "updateBlacklist", userProp);
        return new MessageResponse(0, "变更黑名单档案完成！");
    }

    /**
     * @throws
     * @Title:selectBlacklistByPrimaryKey
     * @Description: TODO(获取黑名单档案)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Blacklist>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public SingleResult<BlacklistVo> selectBlacklistByPrimaryKey(String id) throws Exception {
        SingleResult<BlacklistVo> rst = new SingleResult<BlacklistVo>();
        rst.setValue(this.blacklistDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteBlacklistByBlacklistId
     * @Description: TODO(删除黑名单档案)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse deleteBlacklistByBlacklistId(String id,
                                                        UserProp userProp) throws Exception {
        this.blacklistDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除黑名单档案", "黑名单档案", String.valueOf(id),
                String.valueOf(id), "黑名单档案", userProp);
        return new MessageResponse(0, "黑名单档案删除完成！");
    }
}
