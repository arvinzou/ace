package com.huacainfo.ace.portal.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import com.huacainfo.ace.portal.dao.AppealDao;
import com.huacainfo.ace.portal.model.Appeal;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.AppealService;
import com.huacainfo.ace.portal.vo.AppealVo;
import com.huacainfo.ace.portal.vo.AppealQVo;

@Service("appealService")
/**
 * @author: 陈晓克
 * @version: 2018-05-14
 * @Description: TODO(诉求模板)
 */
public class AppealServiceImpl implements AppealService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AppealDao appealDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(诉求模板分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<AppealVo>
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public PageResult<AppealVo> findAppealList(AppealQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<AppealVo> rst = new PageResult<AppealVo>();
        List<AppealVo> list = this.appealDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.appealDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertAppeal
     * @Description: TODO(添加诉求模板)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public MessageResponse insertAppeal(Appeal o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "诉求名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcoteUrl())) {
            return new MessageResponse(1, "二维码不能为空！");
        }
        if (CommonUtils.isBlank(o.getRemark())) {
            return new MessageResponse(1, "诉求简介不能为空！");
        }
        int temp = this.appealDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "诉求模板名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.appealDao.insert(o);
        this.dataBaseLogService.log("添加诉求模板", "诉求模板", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "添加诉求模板完成！");
    }

    /**
     * @throws
     * @Title:updateAppeal
     * @Description: TODO(更新诉求模板)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public MessageResponse updateAppeal(Appeal o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getName())) {
            return new MessageResponse(1, "诉求名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getCover())) {
            return new MessageResponse(1, "封面不能为空！");
        }
        if (CommonUtils.isBlank(o.getQrcoteUrl())) {
            return new MessageResponse(1, "二维码不能为空！");
        }
        if (CommonUtils.isBlank(o.getRemark())) {
            return new MessageResponse(1, "诉求简介不能为空！");
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
        this.appealDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更诉求模板", "诉求模板", "", o.getName(),
                o.getName(), userProp);
        return new MessageResponse(0, "变更诉求模板完成！");
    }

    /**
     * @throws
     * @Title:selectAppealByPrimaryKey
     * @Description: TODO(获取诉求模板)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Appeal>
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public SingleResult<AppealVo> selectAppealByPrimaryKey(String id) throws Exception {
        SingleResult<AppealVo> rst = new SingleResult<AppealVo>();
        rst.setValue(this.appealDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteAppealByAppealId
     * @Description: TODO(删除诉求模板)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 陈晓克
     * @version: 2018-05-14
     */
    @Override
    public MessageResponse deleteAppealByAppealId(String id,
                                                  UserProp userProp) throws Exception {
        this.appealDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除诉求模板", "诉求模板", String.valueOf(id),
                String.valueOf(id), "诉求模板", userProp);
        return new MessageResponse(0, "诉求模板删除完成！");
    }
}
