package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;

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
import com.huacainfo.ace.woc.dao.DeviceDao;
import com.huacainfo.ace.woc.model.Device;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.DeviceService;
import com.huacainfo.ace.woc.vo.DeviceVo;
import com.huacainfo.ace.woc.vo.DeviceQVo;

@Service("deviceService")
/**
 * @author: Arvin
 * @version: 2018-03-12
 * @Description: TODO(设备档案)
 */
public class DeviceServiceImpl implements DeviceService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DeviceDao deviceDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(设备档案分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<DeviceVo>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public PageResult<DeviceVo> findDeviceList(DeviceQVo condition, int start,
                                               int limit, String orderBy) throws Exception {
        PageResult<DeviceVo> rst = new PageResult<DeviceVo>();
        List<DeviceVo> list = this.deviceDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.deviceDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertDevice
     * @Description: TODO(添加设备档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse insertDevice(Device o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        //o.setId(String.valueOf(new Date().getTime()));
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceName())) {
            return new MessageResponse(1, "设备名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceNo())) {
            return new MessageResponse(1, "设备编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceType())) {
            return new MessageResponse(1, "设备类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceStatus())) {
            return new MessageResponse(1, "设备状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }
        if (CommonUtils.isBlank(o.getLastModifyDate())) {
            return new MessageResponse(1, "最后更新时间不能为空！");
        }

        int temp = this.deviceDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "设备档案名称重复！");
        }
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.deviceDao.insert(o);
        this.dataBaseLogService.log("添加设备档案", "设备档案", "", "insertDevice",
                "insertDevice", userProp);
        return new MessageResponse(0, "添加设备档案完成！");
    }

    /**
     * @throws
     * @Title:updateDevice
     * @Description: TODO(更新设备档案)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse updateDevice(Device o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceName())) {
            return new MessageResponse(1, "设备名称不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceNo())) {
            return new MessageResponse(1, "设备编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceType())) {
            return new MessageResponse(1, "设备类型不能为空！");
        }
        if (CommonUtils.isBlank(o.getDeviceStatus())) {
            return new MessageResponse(1, "设备状态不能为空！");
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
        this.deviceDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更设备档案", "设备档案", "", "updateDevice",
               "updateDevice", userProp);
        return new MessageResponse(0, "变更设备档案完成！");
    }

    /**
     * @throws
     * @Title:selectDeviceByPrimaryKey
     * @Description: TODO(获取设备档案)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Device>
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public SingleResult<DeviceVo> selectDeviceByPrimaryKey(String id) throws Exception {
        SingleResult<DeviceVo> rst = new SingleResult<DeviceVo>();
        rst.setValue(this.deviceDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteDeviceByDeviceId
     * @Description: TODO(删除设备档案)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2018-03-12
     */
    @Override
    public MessageResponse deleteDeviceByDeviceId(String id,
                                                  UserProp userProp) throws Exception {
        this.deviceDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除设备档案", "设备档案", String.valueOf(id),
                String.valueOf(id), "设备档案", userProp);
        return new MessageResponse(0, "设备档案删除完成！");
    }
}
