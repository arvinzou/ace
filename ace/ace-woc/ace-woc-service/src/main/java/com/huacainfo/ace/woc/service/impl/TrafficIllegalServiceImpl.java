package com.huacainfo.ace.woc.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.dao.TrafficIllegalDao;
import com.huacainfo.ace.woc.model.TrafficIllegal;
import com.huacainfo.ace.woc.service.TrafficIllegalService;
import com.huacainfo.ace.woc.vo.TrafficIllegalQVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("trafficIllegalService")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(通行违章记录)
 */
public class TrafficIllegalServiceImpl implements TrafficIllegalService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TrafficIllegalDao trafficIllegalDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通行违章记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<TrafficIllegalVo>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public PageResult<TrafficIllegalVo> findTrafficIllegalList(TrafficIllegalQVo condition, int start,
                                                               int limit, String orderBy) throws Exception {
        PageResult<TrafficIllegalVo> rst = new PageResult<TrafficIllegalVo>();
        List<TrafficIllegalVo> list = this.trafficIllegalDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.trafficIllegalDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTrafficIllegal
     * @Description: TODO(添加通行违章记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse insertTrafficIllegal(TrafficIllegal o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        //o.setId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditTime())) {
            return new MessageResponse(1, "确认时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditor())) {
            return new MessageResponse(1, "审核人不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.trafficIllegalDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "通行违章记录名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.trafficIllegalDao.insertSelective(o);
        this.dataBaseLogService.log("添加通行违章记录", "通行违章记录", "", o.getTrafficId(),
                o.getTrafficId(), userProp);
        return new MessageResponse(0, "添加通行违章记录完成！");
    }

    /**
     * @throws
     * @Title:insertTrafficIllegal
     * @Description: TODO(添加通行违章记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse insertTrafficIllegalII(TrafficIllegal o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }

        int temp = this.trafficIllegalDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "通行违章记录名称重复！");
        }

        o.setAuditTime(new Date());
        o.setAuditor(userProp.getName());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.trafficIllegalDao.insertSelective(o);
        this.dataBaseLogService.log("添加通行违章记录", "通行违章记录", "", o.getTrafficId(),
                o.getTrafficId(), userProp);
        return new MessageResponse(0, "添加通行违章记录完成！");
    }

    /**
     * @throws
     * @Title:updateTrafficIllegal
     * @Description: TODO(更新通行违章记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse updateTrafficIllegal(TrafficIllegal o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditTime())) {
            return new MessageResponse(1, "确认时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getAuditor())) {
            return new MessageResponse(1, "审核人不能为空！");
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
        this.trafficIllegalDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更通行违章记录", "通行违章记录", "", o.getTrafficId(),
                o.getTrafficId(), userProp);
        return new MessageResponse(0, "变更通行违章记录完成！");
    }

    /**
     * @throws
     * @Title:selectTrafficIllegalByPrimaryKey
     * @Description: TODO(获取通行违章记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TrafficIllegal>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public SingleResult<TrafficIllegalVo> selectTrafficIllegalByPrimaryKey(String id) throws Exception {
        SingleResult<TrafficIllegalVo> rst = new SingleResult<TrafficIllegalVo>();
        rst.setValue(this.trafficIllegalDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTrafficIllegalByTrafficIllegalId
     * @Description: TODO(删除通行违章记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse deleteTrafficIllegalByTrafficIllegalId(String id,
                                                                  UserProp userProp) throws Exception {
        this.trafficIllegalDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除通行违章记录", "通行违章记录", String.valueOf(id),
                String.valueOf(id), "通行违章记录", userProp);
        return new MessageResponse(0, "通行违章记录删除完成！");
    }
}
