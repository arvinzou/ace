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
import com.huacainfo.ace.woc.dao.MonitorSiteDao;
import com.huacainfo.ace.woc.model.MonitorSite;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.MonitorSiteService;
import com.huacainfo.ace.woc.vo.MonitorSiteVo;
import com.huacainfo.ace.woc.vo.MonitorSiteQVo;

@Service("monitorSiteService")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description: TODO(监控点档案)
 */
public class MonitorSiteServiceImpl implements MonitorSiteService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MonitorSiteDao monitorSiteDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(监控点档案分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<MonitorSiteVo>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public PageResult<MonitorSiteVo> findMonitorSiteList(MonitorSiteQVo condition, int start,
                                                         int limit, String orderBy) throws Exception {
        PageResult<MonitorSiteVo> rst = new PageResult<MonitorSiteVo>();
        List<MonitorSiteVo> list = this.monitorSiteDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.monitorSiteDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMonitorSite
     * @Description: TODO(添加监控点档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse insertMonitorSite(MonitorSite o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        o.setLastModifyDate(DateUtil.getNowDate());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSiteId())) {
            return new MessageResponse(1, "卡点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getMonitorSiteName())) {
            return new MessageResponse(1, "监控点名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getMonitorSiteStatus())) {
            return new MessageResponse(1, "监控点状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.monitorSiteDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "监控点档案名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.monitorSiteDao.insertSelective(o);
        this.dataBaseLogService.log("添加监控点档案", "监控点档案", "", "insertMonitorSite",
                "insertMonitorSite", userProp);
        return new MessageResponse(0, "添加监控点档案完成！");
    }

    /**
     * @throws
     * @Title:updateMonitorSite
     * @Description: TODO(更新监控点档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse updateMonitorSite(MonitorSite o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getSiteId())) {
            return new MessageResponse(1, "卡点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getMonitorSiteName())) {
            return new MessageResponse(1, "监控点名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getMonitorSiteStatus())) {
            return new MessageResponse(1, "监控点状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.monitorSiteDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更监控点档案", "监控点档案", "", "updateMonitorSite",
                "updateMonitorSite", userProp);
        return new MessageResponse(0, "变更监控点档案完成！");
    }

    /**
     * @throws
     * @Title:selectMonitorSiteByPrimaryKey
     * @Description: TODO(获取监控点档案)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MonitorSite>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public SingleResult<MonitorSiteVo> selectMonitorSiteByPrimaryKey(String id) throws Exception {
        SingleResult<MonitorSiteVo> rst = new SingleResult<MonitorSiteVo>();
        rst.setValue(this.monitorSiteDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMonitorSiteByMonitorSiteId
     * @Description: TODO(删除监控点档案)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse deleteMonitorSiteByMonitorSiteId(String id,
                                                            UserProp userProp) throws Exception {
        this.monitorSiteDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除监控点档案", "监控点档案", String.valueOf(id),
                String.valueOf(id), "监控点档案", userProp);
        return new MessageResponse(0, "监控点档案删除完成！");
    }
}
