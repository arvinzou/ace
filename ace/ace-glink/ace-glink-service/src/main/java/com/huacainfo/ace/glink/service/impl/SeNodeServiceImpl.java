package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.SeApiToolKit;
import com.huacainfo.ace.glink.api.pojo.fe.JackBoxOut;
import com.huacainfo.ace.glink.api.pojo.fe.MeterBoxOut;
import com.huacainfo.ace.glink.api.pojo.fe.NodeMonitorDataOut;
import com.huacainfo.ace.glink.dao.*;
import com.huacainfo.ace.glink.model.*;
import com.huacainfo.ace.glink.service.SeNodeService;
import com.huacainfo.ace.glink.vo.SeNodeMonitorVo;
import com.huacainfo.ace.glink.vo.SeNodeQVo;
import com.huacainfo.ace.glink.vo.SeNodeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("seNodeService")
/**
 * @author: Arvin
 * @version: 2019-04-18
 * @Description: TODO(配电箱数据)
 */
public class SeNodeServiceImpl implements SeNodeService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeNodeDao seNodeDao;
    @Autowired
    private SeNodeDeviceDao seNodeDeviceDao;
    @Autowired
    private SeNodeMonitorDao seNodeMonitorDao;
    @Autowired
    private SeNodeMonitorDeviceDao seNodeMonitorDeviceDao;
    @Autowired
    private SeNodeMonitorDeviceChDao seNodeMonitorDeviceChDao;
    @Autowired
    private SeNodeMeterDao seNodeMeterDao;

    @Autowired
    private DataBaseLogService dataBaseLogService;


    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(配电箱数据分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<SeNodeVo>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public PageResult<SeNodeVo> findSeNodeList(SeNodeQVo condition,
                                               int start, int limit, String orderBy) throws Exception {
        PageResult<SeNodeVo> rst = new PageResult<>();
        List<SeNodeVo> list = seNodeDao.findList(condition, start, limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.seNodeDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertSeNode
     * @Description: TODO(添加配电箱数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse insertSeNode(SeNode o, UserProp userProp) throws Exception {
        String guid = StringUtil.isEmpty(o.getId()) ? GUIDUtil.getGUID() : o.getId();
        o.setId(guid);

        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeID())) {
            return new MessageResponse(1, "配电箱编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        int temp = this.seNodeDao.isExist(o);
        if (temp > 0) {
            return new MessageResponse(1, "配电箱数据名称重复！");
        }


        o.setCreateDate(new Date());
        o.setStatus("1");
        this.seNodeDao.insert(o);
        this.dataBaseLogService.log("添加配电箱数据", "配电箱数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:updateSeNode
     * @Description: TODO(更新配电箱数据)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse updateSeNode(SeNode o, UserProp userProp) throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getNodeID())) {
            return new MessageResponse(1, "配电箱编号不能为空！");
        }
        if (CommonUtils.isBlank(o.getStatus())) {
            return new MessageResponse(1, "状态不能为空！");
        }


        this.seNodeDao.updateByPrimaryKey(o);
        this.dataBaseLogService.log("变更配电箱数据", "配电箱数据", "",
                o.getId(), o.getId(), userProp);

        return new MessageResponse(0, "保存成功！");
    }

    /**
     * @throws
     * @Title:deleteSeNodeBySeNodeId
     * @Description: TODO(删除配电箱数据)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public MessageResponse deleteSeNodeBySeNodeId(String id, UserProp userProp) throws
            Exception {
        this.seNodeDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除配电箱数据", "配电箱数据", id, id,
                "配电箱数据", userProp);
        return new MessageResponse(0, "删除成功！");
    }

    /**
     * @throws
     * @Title:selectSeNodeByPrimaryKey
     * @Description: TODO(获取配电箱数据)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<SeNode>
     * @author: Arvin
     * @version: 2019-04-18
     */
    @Override
    public SingleResult<SeNodeVo> selectSeNodeByPrimaryKey(String id) throws Exception {
        SingleResult<SeNodeVo> rst = new SingleResult<>();
        SeNodeVo vo = seNodeDao.selectVoByPrimaryKey(id);
        //setDeviceList
        SeNodeDevice condition = new SeNodeDevice();
        condition.setNodeID(vo.getNodeID());
        vo.setDeviceList(seNodeDeviceDao.findList(condition, 0, 100, ""));

        rst.setValue(vo);
        return rst;
    }

    /**
     * 同步配电箱基础数据
     *
     * @param userProp 操作用户
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse syncData(UserProp userProp) {
        //http请求，获取远程服务器数据
        JackBoxOut out = null;
        try {
            out = SeApiToolKit.getBaseNodeInfo();
        } catch (Exception e) {
            logger.error("[SeNodeServiceImpl.syncData]接口获取数据异常=>{}", e);
            return new MessageResponse(ResultCode.FAIL, "接口获取数据异常");
        }
        //1、清理库中原有数据
        seNodeDao.allClear();
        //2、填充获取的新数据
        List<JackBoxOut.JackBox> nodeGroup = out.getNodeGroup();
        SeNode seNode;
        for (JackBoxOut.JackBox item : nodeGroup) {
            seNode = new SeNode();
            seNode.setId(GUIDUtil.getGUID());
            seNode.setNodeCount(out.getNodeCount());
            seNode.setNodeID(item.getNodeID());
            seNode.setLocal(item.getLocal());
            seNode.setiPAddress(item.getIPAddress());
            seNode.setRouteIPAddress(item.getRouteIPAddress());
            seNode.setAreaNodeID(item.getAreaNodeID());
            seNode.setpX(item.getPX());
            seNode.setpY(item.getPY());
            seNode.setMeterID(item.getMeterID());
            seNode.setMeterType(item.getMeterType());
            seNode.setEngineer(item.getEngineer());
            seNode.setTel(item.getTel());
            seNode.setDeviceCount(item.getDeviceCount());
            seNode.setRemark("测试数据填充");
            seNode.setStatus("1");
            seNode.setCreateDate(DateUtil.getNowDate());
            //插入模块内容
            recurInsertDevice(seNode, item.getDeviceData());
            //插入配电箱
            seNodeDao.insert(seNode);
        }

        return new MessageResponse(ResultCode.SUCCESS, "同步成功");
    }

    private void recurInsertDevice(SeNode seNode, List<JackBoxOut.Device> deviceData) {
        if (CollectionUtils.isEmpty(deviceData)) {
            return;
        }
        SeNodeDevice device;
        for (JackBoxOut.Device item : deviceData) {
            device = new SeNodeDevice();
            device.setId(GUIDUtil.getGUID());
            device.setStatus("1");
//            device.setRemark("测试数据填充");
            device.setCreateDate(DateUtil.getNowDate());
            //
            device.setNodeID(seNode.getNodeID());
            device.setDeviceType(item.getDeviceType());
            device.setDeviceCode(item.getDeviceCode());
            device.setDeviceBox(item.getDeviceBox());
            device.setcH1Name(item.getCH1Name());
            device.setcH2Name(item.getCH2Name());
            device.setcH3Name(item.getCH3Name());
            device.setcH4Name(item.getCH4Name());
            device.setcH5Name(item.getCH5Name());
            device.setcH6Name(item.getCH6Name());
            device.setcH7Name(item.getCH7Name());
            device.setcH8Name(item.getCH8Name());
            device.setcH9Name(item.getCH9Name());
            device.setcH10Name(item.getCH10Name());
            device.setcH11Name(item.getCH11Name());
            device.setcH12Name(item.getCH12Name());
            device.setcH1Control(item.getCH1Control());
            device.setcH2Control(item.getCH2Control());
            device.setcH3Control(item.getCH3Control());
            device.setcH4Control(item.getCH4Control());
            device.setcH5Control(item.getCH5Control());
            device.setcH6Control(item.getCH6Control());
            device.setcH7Control(item.getCH7Control());
            device.setcH8Control(item.getCH8Control());
            device.setcH9Control(item.getCH9Control());
            device.setcH10Control(item.getCH10Control());
            device.setcH11Control(item.getCH11Control());
            device.setcH12Control(item.getCH12Control());

            seNodeDeviceDao.insert(device);
        }
    }


    /**
     * 同步配电箱监测数据
     *
     * @param curUserProp
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse syncMonitorData(UserProp curUserProp) {
        //0-1、获取所有配电箱数据
        String nodeGroup = findNodeGroup();
        if (nodeGroup.isEmpty()) {
            return new MessageResponse(ResultCode.FAIL, "配电箱基础数据缺失");
        }
        //http请求，获取远程服务器数据
        NodeMonitorDataOut out = null;
        try {
            out = SeApiToolKit.getNodeMonitorListData(nodeGroup);
        } catch (Exception e) {
            logger.error("[SeNodeServiceImpl.syncMonitorData]接口获取数据异常=>{}", e);
            return new MessageResponse(ResultCode.FAIL, "接口获取数据异常");
        }
        //1、清理库中原有数据
        seNodeMonitorDao.allClear();
        //2、填充获取的新数据
        List<NodeMonitorDataOut.NodeMonitorData> nodeData = out.getNodeData();
        SeNodeMonitor monitor;
        for (NodeMonitorDataOut.NodeMonitorData item : nodeData) {
            monitor = new SeNodeMonitor();
            monitor.setId(GUIDUtil.getGUID());
            monitor.setStatus("1");
            monitor.setCreateDate(DateUtil.getNowDate());
            //
            monitor.setNodeID(item.getNodeID());
            monitor.setReportTime(item.getReportTime());
            monitor.setGateStatus(item.getGateStatus());
            monitor.setGateReportTime(item.getGateReportTime());
            monitor.setRouteStatus(item.getRouteStatus());
            monitor.setRouteSignal(item.getRouteSignal());
            monitor.setRouteReportTime(item.getRouteReportTime());
            monitor.setCurrentPreset(item.getCurrentPreset());
            monitor.setPresetCaption(item.getPresetCaption());
            monitor.setPresetReportTime(item.getPresetReportTime());
            monitor.setTemperature(item.getTemperature());
            monitor.setHumidity(item.getHumidity());
            monitor.setwSDReportTime(item.getWSDReportTime());
            monitor.setDoorStatus(item.getDoorStatus());
            monitor.setMeterID(item.getMeterID());
            if (StringUtil.isNotEmpty(item.getMeterValue())) {
                monitor.setMeterValue(new BigDecimal(item.getMeterValue().replace("kwh", "")));
            }
            monitor.setMeterValueUnit("kwh");
            monitor.setMeterReportTime(DateUtil.getNow());
            //监测模块数据
            recurInsertMonitorDevice(monitor, item.getDeviceData());

            seNodeMonitorDao.insert(monitor);
        }

        return new MessageResponse(ResultCode.SUCCESS, "同步成功");
    }


    private String findNodeGroup() {
        SeNodeQVo condition = new SeNodeQVo();
        List<SeNodeVo> seNodeVoList = this.seNodeDao.findList(condition, 0, 99999, "");
        if (CollectionUtils.isEmpty(seNodeVoList)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (SeNodeVo item : seNodeVoList) {
            sb.append(item.getNodeID()).append(",");
        }

        String nodeGroup = sb.toString();
        return nodeGroup.substring(0, nodeGroup.length() - 1);
    }

    private void recurInsertMonitorDevice(SeNodeMonitor monitor, List<NodeMonitorDataOut.DeviceData> deviceData) {
        if (CollectionUtils.isEmpty(deviceData)) {
            return;
        }
        //
        SeNodeMonitorDevice record;
        for (NodeMonitorDataOut.DeviceData item : deviceData) {
            record = new SeNodeMonitorDevice();
            record.setId(GUIDUtil.getGUID());
            record.setStatus("1");
            record.setCreateDate(DateUtil.getNowDate());
            record.setRemark(monitor.getNodeID());
            //
            record.setNodeID(monitor.getNodeID());
            record.setDeviceType(item.getDeviceType());
            record.setDeviceCode(item.getDeviceCode());
            record.setDeviceBox(item.getDeviceBox());
            record.setDeviceStatus(item.getDeviceStatus());
            record.setDeviceReportTime(item.getDeviceReportTime());
            //
            recurInsertMonitorDeviceCH(item);

            seNodeMonitorDeviceDao.insert(record);
        }
    }

    private void recurInsertMonitorDeviceCH(NodeMonitorDataOut.DeviceData item) {
        //CH1Value
        if (item.getCH1Value() != null) {
            insertMonitorDeviceCH("CH1Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH1Value());
        }
        //CH2Value
        if (item.getCH2Value() != null) {
            insertMonitorDeviceCH("CH2Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH2Value());
        }
        //CH3Value
        if (item.getCH3Value() != null) {
            insertMonitorDeviceCH("CH3Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH3Value());
        }
        //CH4Value
        if (item.getCH4Value() != null) {
            insertMonitorDeviceCH("CH4Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH4Value());
        }
        //CH5Value
        if (item.getCH5Value() != null) {
            insertMonitorDeviceCH("CH5Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH5Value());
        }
        //CH6Value
        if (item.getCH6Value() != null) {
            insertMonitorDeviceCH("CH6Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH6Value());
        }
        //CH7Value
        if (item.getCH7Value() != null) {
            insertMonitorDeviceCH("CH7Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH7Value());
        }
        //CH8Value
        if (item.getCH8Value() != null) {
            insertMonitorDeviceCH("CH8Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH8Value());
        }
        //CH9Value
        if (item.getCH9Value() != null) {
            insertMonitorDeviceCH("CH9Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH9Value());
        }
        //CH10Value
        if (item.getCH10Value() != null) {
            insertMonitorDeviceCH("CH10Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH10Value());
        }
        //CH11Value
        if (item.getCH11Value() != null) {
            insertMonitorDeviceCH("CH11Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH11Value());
        }
        //CH12Value
        if (item.getCH12Value() != null) {
            insertMonitorDeviceCH("CH12Value", item.getDeviceCode(), item.getDeviceBox(), item.getCH12Value());
        }
    }

    private int insertMonitorDeviceCH(String CHName,
                                      String deviceCode,
                                      int deviceBox,
                                      NodeMonitorDataOut.CHValue data) {
        SeNodeMonitorDeviceCh ch = new SeNodeMonitorDeviceCh();
        ch.setId(GUIDUtil.getGUID());
        ch.setCreateDate(DateUtil.getNowDate());
        //
        ch.setChName(CHName);
        ch.setDeviceCode(deviceCode);
        ch.setDeviceBox(deviceBox);
        ch.setStatus(data.getStatus());
        ch.setcHReportTime(data.getCHReportTime());
        ch.setVa(data.getVa());
        ch.setVb(data.getVb());
        ch.setVc(data.getVc());
        ch.setIa(data.getIa());
        ch.setIb(data.getIb());
        ch.setIc(data.getIc());
        ch.setPa(data.getPa());
        ch.setPb(data.getPb());
        ch.setPc(data.getPc());

        return seNodeMonitorDeviceChDao.insert(ch);
    }


    /**
     * 获取配电箱监控数据
     *
     * @param nodeID 配电箱编号
     * @return SingleResult<SeNodeMonitorVo>
     * @throws Exception
     */
    @Override
    public SingleResult<SeNodeMonitorVo> getMonitorData(String nodeID) {

        SingleResult<SeNodeMonitorVo> rst = new SingleResult<>();
        //vo
        SeNodeMonitorVo vo = seNodeMonitorDao.findByNodeID(nodeID);
        //setDeviceList
        vo.setMonitorDeviceList(seNodeMonitorDeviceDao.findByNodeID(nodeID));

        rst.setValue(vo);
        return rst;
    }

    /**
     * 获取配电箱监控数据
     *
     * @param deviceCode 模块代码
     * @param chName     回路名称
     * @return SingleResult<SeNodeMonitorDeviceCh>
     * @throws Exception
     */
    @Override
    public SingleResult<SeNodeMonitorDeviceCh> getMonitorDeviceCH(String deviceCode, String chName, String deviceBox) {
        SingleResult<SeNodeMonitorDeviceCh> rst = new SingleResult<>();
        rst.setValue(seNodeMonitorDeviceChDao.findByCHName(deviceCode, chName, deviceBox));
        return rst;
    }

    /**
     * 同步配电箱全部电表数据*
     *
     * @param userProp 操作员
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse syncNodeMeterData(UserProp userProp) {
        //0-接口调用，获取数据源
        MeterBoxOut out;
        try {
            out = SeApiToolKit.getAllMeterData();
        } catch (Exception e) {
            logger.error("[SeNodeServiceImpl.syncNodeMeterData]接口获取数据异常=>{}", e);
            return new MessageResponse(ResultCode.FAIL, "接口获取数据异常");
        }
        //1-清空库存
        seNodeMeterDao.allClear();
        //2-添加库存
        SeNodeMeter meter;
        for (MeterBoxOut.MeterData item : out.getNodeMeterData()) {
            meter = new SeNodeMeter();
            meter.setId(GUIDUtil.getGUID());
            meter.setStatus("1");
            meter.setCreateDate(DateUtil.getNowDate());
            //
            meter.setNodeID(item.getNodeID());
            meter.setMeterID(item.getMeterID());
            if (StringUtil.isNotEmpty(item.getMeterValue())) {
                meter.setMeterValue(new BigDecimal(item.getMeterValue().replace("kwh", "")));
            }
            meter.setMeterValueUnit("kwh");
            meter.setUpdateTime(item.getUpdateTime());

            seNodeMeterDao.insert(meter);
        }

        return new MessageResponse(ResultCode.SUCCESS, "数据同步成功");
    }

    @Override
    public List<Tree> getNodeTreeList(String id) {
        List<Map<String, Object>> dataList = seNodeDao.selectNodeTreeList();

        CommonTreeUtils commonTreeUtils = new CommonTreeUtils(dataList);
        return commonTreeUtils.getTreeList(id);
    }

    @Override
    public SeNodeMonitorVo findByNodeId(String nodeId) {
        return seNodeMonitorDao.findByNodeID(nodeId);
    }

}
