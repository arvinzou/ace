package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.woc.vo.TrafficVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.dao.TrafficSubDao;
import com.huacainfo.ace.woc.model.TrafficSub;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.TrafficSubService;
import com.huacainfo.ace.woc.vo.TrafficSubVo;
import com.huacainfo.ace.woc.vo.TrafficSubQVo;
import com.huacainfo.ace.woc.dao.TrafficDao;

@Service("trafficSubService")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(通行记录子表)
 */
public class TrafficSubServiceImpl implements TrafficSubService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TrafficSubDao trafficSubDao;
    @Autowired
    private TrafficDao trafficDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通行记录子表分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<TrafficSubVo>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public PageResult<TrafficSubVo> findTrafficSubList(TrafficSubQVo condition, int start,
                                                       int limit, String orderBy) throws Exception {
        PageResult<TrafficSubVo> rst = new PageResult<TrafficSubVo>();
        List<TrafficSubVo> list = this.trafficSubDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.trafficSubDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTrafficSub
     * @Description: TODO(添加通行记录子表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse insertTrafficSubAuto(TrafficSub o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "资料类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getInspectTime())) {
            return new MessageResponse(1, "检查时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getPlateNo())) {
            return new MessageResponse(1, "车牌号不能为空！");
        }
        if (CommonUtils.isBlank(o.getFileUrl())) {
            return new MessageResponse(1, "资料地址不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.trafficSubDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "通行记录子表名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.trafficSubDao.insertSelective(o);
        this.dataBaseLogService.log("添加通行记录子表", "通行记录子表", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加通行记录子表完成！");
    }

    /**
     * @throws
     * @Title:insertTrafficSub
     * @Description: TODO(添加通行记录子表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse insertTrafficSub(TrafficSub o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        TrafficVo t = trafficDao.selectByPrimaryKey(o.getTrafficId());

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "资料类型不能为空！");
        }
        o.setInspectTime(t.getInspectTime());
        if (CommonUtils.isBlank(o.getInspectTime())) {
            return new MessageResponse(1, "检查时间不能为空！");
        }
        o.setPlateNo(t.getPlateNo());
        if (CommonUtils.isBlank(o.getPlateNo())) {
            return new MessageResponse(1, "车牌号不能为空！");
        }
        if (CommonUtils.isBlank(o.getFileUrl())) {
            return new MessageResponse(1, "资料地址不能为空！");
        }
//        int temp = this.trafficSubDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "通行记录子表名称重复！");
//        }
        o.setCreateDate(new Date());
        o.setLastModifyDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.trafficSubDao.insertSelective(o);
        this.dataBaseLogService.log("添加通行记录子表", "通行记录子表", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加通行记录子表完成！");
    }

    /**
     * @throws
     * @Title:updateTrafficSub
     * @Description: TODO(更新通行记录子表)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse updateTrafficSub(TrafficSub o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getTrafficId())) {
            return new MessageResponse(1, "通行记录主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getCategory())) {
            return new MessageResponse(1, "资料类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getInspectTime())) {
            return new MessageResponse(1, "检查时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getPlateNo())) {
            return new MessageResponse(1, "车牌号不能为空！");
        }
        if (CommonUtils.isBlank(o.getFileUrl())) {
            return new MessageResponse(1, "资料地址不能为空！");
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
        this.trafficSubDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更通行记录子表", "通行记录子表", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更通行记录子表完成！");
    }

    /**
     * @throws
     * @Title:selectTrafficSubByPrimaryKey
     * @Description: TODO(获取通行记录子表)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<TrafficSub>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public SingleResult<TrafficSubVo> selectTrafficSubByPrimaryKey(String id) throws Exception {
        SingleResult<TrafficSubVo> rst = new SingleResult<TrafficSubVo>();
        rst.setValue(this.trafficSubDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTrafficSubByTrafficSubId
     * @Description: TODO(删除通行记录子表)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse deleteTrafficSubByTrafficSubId(String id,
                                                          UserProp userProp) throws Exception {
        this.trafficSubDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除通行记录子表", "通行记录子表", String.valueOf(id),
                String.valueOf(id), "通行记录子表", userProp);
        return new MessageResponse(0, "通行记录子表删除完成！");
    }
}
