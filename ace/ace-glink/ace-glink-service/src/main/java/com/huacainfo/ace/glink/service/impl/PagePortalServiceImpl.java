package com.huacainfo.ace.glink.service.impl;

import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.LeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.base.LeBaseOut;
import com.huacainfo.ace.glink.api.pojo.le.LampStatusOut;
import com.huacainfo.ace.glink.constant.CommConstant;
import com.huacainfo.ace.glink.constant.PortalKey;
import com.huacainfo.ace.glink.dao.PagePortalDao;
import com.huacainfo.ace.glink.model.PagePortal;
import com.huacainfo.ace.glink.service.LeLampStatusService;
import com.huacainfo.ace.glink.service.PagePortalService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName PagePortalServiceImpl
 * @Description TODO
 * @Author Arvin Zou
 * @Date 2019/4/22 15:55
 */
@Service("pagePortalService")
public class PagePortalServiceImpl implements PagePortalService {
    private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PagePortalDao pagePortalDao;
    @Autowired
    private LeLampStatusService leLampStatusService;

    /**
     * 根据key索引
     *
     * @param itemKey key值
     * @param sysId   系统ID
     * @return PagePortal
     */
    @Override
    public PagePortal findByKey(String itemKey, String sysId) {
        return pagePortalDao.findByKey(itemKey, sysId);
    }

    /**
     * 根据系统ID，获取所有key-value
     *
     * @param sysId 系统ID
     * @return List<PagePortal>
     */
    @Override
    public List<PagePortal> findListBySysId(String sysId) {
        return pagePortalDao.findListBySysId(sysId);
    }

    private int insertOrUpdate(String sysId, PagePortal obj, String name, String key, String value) {
        if (obj == null) {
            obj = new PagePortal();
            //
            obj.setItemName(name);
            obj.setItemKey(key);
            obj.setItemValue(value);
            obj.setRemark(name);
            //
            obj.setSysId(sysId);
            obj.setId(GUIDUtil.getGUID());
            obj.setCreateDate(DateUtil.getNowDate());
            return pagePortalDao.insert(obj);
        } else {
            obj.setSysId(sysId);
            obj.setItemName(name);
            obj.setItemKey(key);
            obj.setItemValue(value);
            obj.setRemark(name);
            //
            obj.setCreateDate(DateUtil.getNowDate());
            return pagePortalDao.updateByPrimaryKey(obj);
        }
    }

    /**
     * 每隔[5]分钟,调用一次弱电接口，获取设备总数&故障设备总数
     */
    @Override
    public MessageResponse getLampStatus() {
        LampStatusOut rst;
        try {
            rst = LeApiToolKit.getLampStatus();
        } catch (Exception e) {
            logger.error("[PagePortalServiceImpl.getLampStatus]接口获取数据异常=>{}", e);
            return new MessageResponse(ResultCode.FAIL, "接口获取数据异常");
        }

        if (rst.getCode() == LeBaseOut.SUCCESS) {
            LampStatusOut.LampStatus obj = rst.getData();
            int newVal1 = obj.getLampCount();
            int newVal2 = obj.getBrokenLampCount();
            savePagePortal(newVal1, newVal2);
            return new MessageResponse(ResultCode.SUCCESS, "更新成功!");
        } else {
            logger.error("[弱电接口失败]-[武汉设备状态接口（GetLampStatus）]=>{}", rst.toString());
            return new MessageResponse(ResultCode.FAIL, "弱电接口调用失败");
        }
    }

    private void savePagePortal(int newVal1, int newVal2) {
        //弱电-设备总睡
        PagePortal key1 = findByKey(PortalKey.LE_LampCount, CommConstant.SYS_ID);
        insertOrUpdate(CommConstant.SYS_ID, key1,
                PortalKey.LE_LampCount_ch, PortalKey.LE_LampCount, newVal1 + "");
        //弱电-故障设备总数
        PagePortal key2 = findByKey(PortalKey.LE_BrokenLampCount, CommConstant.SYS_ID);
        insertOrUpdate(CommConstant.SYS_ID, key2,
                PortalKey.LE_BrokenLampCount_ch, PortalKey.LE_BrokenLampCount, newVal2 + "");
    }

    /**
     * 自动同步配电箱全部电表数据
     *
     * @return MessageResponse
     */
    @Override
    public MessageResponse autoSyncNodeMeterData() {
        //todo 缺失统计逻辑

        return null;
    }

    /**
     * 大屏展示数据接口
     *
     * @param params nodeId 节点ID
     * @return Map<String, Object>
     */
    @Override
    public Map<String, Object> screenData(Map<String, String> params) {

        Map<String, Object> rst = new HashMap<>();
        String[] keys = new String[]{
                PortalKey.LE_BuildingTotal,
                PortalKey.SE_RouterOffNum,
                PortalKey.SE_NodeTotal,
                PortalKey.SE_GatewayOffNum,
                PortalKey.SE_NodeDeviceOffNum,
                PortalKey.SE_NodeDeviceNum,
                PortalKey.SE_PowerTotal,
                PortalKey.SE_UnitPrice
        };
        //存储键值对
        List<PagePortal> allList = pagePortalDao.findListByKeys("glink", keys);
        for (PagePortal item : allList) {
            rst.put(item.getItemKey(), item);
        }

        return rst;
    }


}
