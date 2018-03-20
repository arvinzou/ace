package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;

import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.woc.dao.DeviceDao;
import com.huacainfo.ace.woc.dao.MonitorSiteDao;
import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.woc.model.MonitorSite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.dao.MonitorSiteDetailDao;
import com.huacainfo.ace.woc.model.MonitorSiteDetail;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.MonitorSiteDetailService;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailVo;
import com.huacainfo.ace.woc.vo.MonitorSiteDetailQVo;

import javax.annotation.Resource;

@Service("monitorSiteDetailService")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description: TODO(监控点明细档案)
 */
public class MonitorSiteDetailServiceImpl implements MonitorSiteDetailService {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private MonitorSiteDetailDao monitorSiteDetailDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;
    @Resource
    private MonitorSiteDao monitorSiteDao;
    @Resource
    private DeviceDao deviceDao;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(监控点明细档案分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<MonitorSiteDetailVo>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public PageResult<MonitorSiteDetailVo> findMonitorSiteDetailList(MonitorSiteDetailQVo condition, int start,
                                                                     int limit, String orderBy) throws Exception {
        PageResult<MonitorSiteDetailVo> rst = new PageResult<MonitorSiteDetailVo>();
        List<MonitorSiteDetailVo> list = this.monitorSiteDetailDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.monitorSiteDetailDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertMonitorSiteDetail
     * @Description: TODO(添加监控点明细档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse insertMonitorSiteDetail(MonitorSiteDetail o, UserProp userProp)
            throws Exception {

        if (CommonUtils.isBlank(o.getMonitorSiteId())) {
            return new MessageResponse(1, "监控点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceId())) {
            return new MessageResponse(1, "设备ID不能为空！");
        }

//        int temp = this.monitorSiteDetailDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "监控点明细档案名称重复！");
//        }

        o.setId(GUIDUtil.getGUID());
        o.setLastModifyDate(DateUtil.getNowDate());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.monitorSiteDetailDao.insertSelective(o);
        this.dataBaseLogService.log("添加监控点明细档案", "监控点明细档案", "", "insertMonitorSiteDetail",
                "insertMonitorSiteDetail", userProp);
        return new MessageResponse(0, "添加监控点明细档案完成！");
    }

    /**
     * @throws
     * @Title:updateMonitorSiteDetail
     * @Description: TODO(更新监控点明细档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse updateMonitorSiteDetail(MonitorSiteDetail o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getMonitorSiteId())) {
            return new MessageResponse(1, "监控点ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceId())) {
            return new MessageResponse(1, "设备ID不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.monitorSiteDetailDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更监控点明细档案", "监控点明细档案", "", "updateMonitorSiteDetail",
                "updateMonitorSiteDetail", userProp);
        return new MessageResponse(0, "变更监控点明细档案完成！");
    }

    /**
     * @throws
     * @Title:selectMonitorSiteDetailByPrimaryKey
     * @Description: TODO(获取监控点明细档案)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<MonitorSiteDetail>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public SingleResult<MonitorSiteDetailVo> selectMonitorSiteDetailByPrimaryKey(String id) throws Exception {
        SingleResult<MonitorSiteDetailVo> rst = new SingleResult<MonitorSiteDetailVo>();
        rst.setValue(this.monitorSiteDetailDao.selectVoByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteMonitorSiteDetailByMonitorSiteDetailId
     * @Description: TODO(删除监控点明细档案)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse deleteMonitorSiteDetailByMonitorSiteDetailId(String id,
                                                                        UserProp userProp) throws Exception {
        this.monitorSiteDetailDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除监控点明细档案", "监控点明细档案", String.valueOf(id),
                String.valueOf(id), "监控点明细档案", userProp);
        return new MessageResponse(0, "监控点明细档案删除完成！");
    }

    /**
     * 建立设备与监控点之前的关系
     */
    @Override
    public MessageResponse bindMonitorSiteDevice(String monitorSiteId, String deviceIds,
                                                 UserProp curUserProp) throws Exception {
        MonitorSite monitorSite = monitorSiteDao.selectByPrimaryKey(monitorSiteId);
        if (null == monitorSite) {
            return new MessageResponse(1, "监控点档案信息有误！");
        }
        Device device;

        //清楚此前设备绑定关系
        List<MonitorSiteDetail> clearList = monitorSiteDetailDao.findAllDevice(monitorSiteId);
        for (MonitorSiteDetail item : clearList) {
            device = deviceDao.selectByPrimaryKey(item.getDeviceId());
            //使设备处于待上线，可供其他监控点使用
            device.setDeviceStatus("1");//1-待上线,2-已上线,3-已下线
            device.setLastModifyUserId(curUserProp.getUserId());
            device.setLastModifyUserName(curUserProp.getName());
            device.setLastModifyDate(DateUtil.getNowDate());
            deviceDao.updateByPrimaryKeySelective(device);
            //
            monitorSiteDetailDao.deleteByPrimaryKey(item.getId());
        }
        if (CommonUtils.isBlank(deviceIds)) {
            return new MessageResponse(0, "监控点明细设备关系添加完成！");
        }
        //新增新设备关系
        MonitorSiteDetailVo vo;
        MessageResponse response;
        String[] deviceIdArray = deviceIds.split(",");
        for (String deviceId : deviceIdArray) {
            device = deviceDao.selectByPrimaryKey(deviceId);
            if (null == device) {
                return new MessageResponse(1, "设备信息有误！[" + deviceId + "]");
            }
            vo = new MonitorSiteDetailVo();
            vo.setMonitorSiteId(monitorSiteId);
            vo.setDeviceId(deviceId);

            response = insertMonitorSiteDevice(vo, device, curUserProp);
            //添加失败返回
            if (response.getStatus() == 1) {
                return response;
            }
        }

        return new MessageResponse(0, "监控点明细设备关系添加完成！");
    }

    private MessageResponse insertMonitorSiteDevice(MonitorSiteDetailVo vo, Device device,
                                                    UserProp curUserProp) throws Exception {
        int temp = this.monitorSiteDetailDao.isExit(vo);
        if (temp > 0) {
            return new MessageResponse(1, "[" + device.getDeviceName() + "]该设备存在其他绑定关系！");
        }
        //使设备上线
        device.setDeviceStatus("2");//1-待上线,2-已上线,3-已下线
        device.setLastModifyUserId(curUserProp.getUserId());
        device.setLastModifyUserName(curUserProp.getName());
        device.setLastModifyDate(DateUtil.getNowDate());
        deviceDao.updateByPrimaryKeySelective(device);

        return insertMonitorSiteDetail(vo, curUserProp);
    }
}
