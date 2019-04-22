package com.huacainfo.ace.glink.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.plugins.wechat.util.StringUtil;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.glink.api.pojo.fe.JackBoxOut;
import com.huacainfo.ace.glink.api.pojo.fe.MeterBoxOut;
import com.huacainfo.ace.glink.api.pojo.fe.NodeMonitorDataOut;
import com.huacainfo.ace.glink.dao.*;
import com.huacainfo.ace.glink.model.*;
import com.huacainfo.ace.glink.service.SeNodeService;
import com.huacainfo.ace.glink.vo.SeNodeMonitorQVo;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        JackBoxOut out = testData();//SeApiToolKit.getBaseNodeInfo();
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
        SeNodeDevice device;
        for (JackBoxOut.Device item : deviceData) {
            device = new SeNodeDevice();
            device.setId(GUIDUtil.getGUID());
            device.setStatus("1");
            device.setRemark("测试数据填充");
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
        NodeMonitorDataOut out = getTestNodeMonitorDataOut(nodeGroup);//SeApiToolKit.getNodeMonitorListData(nodeGroup);
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
            monitor.setRemark("测试数据导入");
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
            monitor.setMeterValue(new BigDecimal(item.getMeterValue().replace("kwh", "")));
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

        //
        SeNodeMonitorDevice record;
        for (NodeMonitorDataOut.DeviceData item : deviceData) {
            record = new SeNodeMonitorDevice();
            record.setId(GUIDUtil.getGUID());
            record.setStatus("1");
            record.setCreateDate(DateUtil.getNowDate());
            record.setRemark("测试数据导入");
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
        insertMonitorDeviceCH("CH1Value", item.getDeviceCode(), item.getCH1Value());
        //CH2Value
        insertMonitorDeviceCH("CH2Value", item.getDeviceCode(), item.getCH2Value());
        //CH3Value
        insertMonitorDeviceCH("CH3Value", item.getDeviceCode(), item.getCH3Value());
        //CH4Value
        insertMonitorDeviceCH("CH4Value", item.getDeviceCode(), item.getCH4Value());
        //CH5Value
        insertMonitorDeviceCH("CH5Value", item.getDeviceCode(), item.getCH5Value());
        //CH6Value
        insertMonitorDeviceCH("CH6Value", item.getDeviceCode(), item.getCH6Value());
        //CH7Value
        insertMonitorDeviceCH("CH7Value", item.getDeviceCode(), item.getCH7Value());
        //CH8Value
        insertMonitorDeviceCH("CH8Value", item.getDeviceCode(), item.getCH8Value());
        //CH9Value
        insertMonitorDeviceCH("CH9Value", item.getDeviceCode(), item.getCH9Value());
        //CH10Value
        insertMonitorDeviceCH("CH10Value", item.getDeviceCode(), item.getCH10Value());
        //CH11Value
        insertMonitorDeviceCH("CH11Value", item.getDeviceCode(), item.getCH11Value());
        //CH12Value
        insertMonitorDeviceCH("CH12Value", item.getDeviceCode(), item.getCH12Value());
    }

    private int insertMonitorDeviceCH(String CHName, String deviceCode, NodeMonitorDataOut.CHValue data) {
        SeNodeMonitorDeviceCh ch = new SeNodeMonitorDeviceCh();
        ch.setId(GUIDUtil.getGUID());
        ch.setCreateDate(DateUtil.getNowDate());
        ch.setRemark("测试数据导入");
        //
        ch.setChName(CHName);
        ch.setDeviceCode(deviceCode);
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
    public SingleResult<SeNodeMonitorDeviceCh> getMonitorDeviceCH(String deviceCode, String chName) {
        SingleResult<SeNodeMonitorDeviceCh> rst = new SingleResult<>();
        rst.setValue(seNodeMonitorDeviceChDao.findByCHName(deviceCode, chName));
        return rst;
    }

    /**
     * 同步配电箱全部电表数据
     *
     * @param userProp 操作员
     * @return MessageResponse
     * @throws Exception
     */
    @Override
    public MessageResponse syncNodeMeterData(UserProp userProp) {
        //0-接口调用，获取数据源
        MeterBoxOut out = getTestMeterData();// SeApiToolKit.getAllMeterData();
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
            meter.setMeterValue(new BigDecimal(item.getMeterValue().replace("kwh", "")));
            meter.setMeterValueUnit("kwh");
            meter.setUpdateTime(item.getUpdateTime());

            seNodeMeterDao.insert(meter);
        }

        return new MessageResponse(ResultCode.SUCCESS, "数据同步成功");
    }


    /**
     * =====================tes data=====================
     */
    private MeterBoxOut getTestMeterData() {
        List<MeterBoxOut.MeterData> list = new ArrayList<>();
        MeterBoxOut.MeterData item;
        int index;
        for (int i = 0; i < 6; i++) {
            index = i + 1;
            item = new MeterBoxOut.MeterData();
            item.setNodeID(index);
            item.setMeterID("0000000000" + index);
            item.setMeterValue("12" + index + ".56kwh");
            item.setUpdateTime(DateUtil.getNow());
            list.add(item);
        }

        return new MeterBoxOut(6, list);
    }

    private NodeMonitorDataOut getTestNodeMonitorDataOut(String nodeGroup) {
        int NodeCount = 3;//配电箱节点数量
        List<NodeMonitorDataOut.NodeMonitorData> NodeData = getTestNodeMonitorData(nodeGroup);//监测数据数组

        return new NodeMonitorDataOut(NodeCount, NodeData);
    }

    private List<NodeMonitorDataOut.NodeMonitorData> getTestNodeMonitorData(String nodeGroup) {
        String[] array = nodeGroup.split(",");
        List<NodeMonitorDataOut.NodeMonitorData> list = new ArrayList<>();
        NodeMonitorDataOut.NodeMonitorData item;
        String time = DateUtil.getNow();
        int nodeID;
        for (int i = 0; i < array.length; i++) {
            nodeID = Integer.parseInt(array[i]);
            item = new NodeMonitorDataOut.NodeMonitorData();
            item.setNodeID(nodeID);//配电箱编号
            item.setReportTime(time);//报告时间
            item.setGateStatus(1);//网关状态：1-在线，0-离线
            item.setGateReportTime(time);//网关状态报告时间
            item.setRouteStatus(1);//路由器状态：1-在线，0-离线
            item.setRouteSignal("60dbm");//路由器信号值
            item.setRouteReportTime(time);//路由器状态报告时间
            item.setCurrentPreset(1);//当前场景
            item.setPresetCaption("全开模式");//当前场景描述
            item.setPresetReportTime(time);//场景报告时间
            item.setTemperature("2" + i + "℃");//温度
            item.setHumidity("2" + i + "%RH");//湿度
            item.setWSDReportTime(time);//温湿度上报时间
            item.setDoorStatus("关闭");//门状态（未知、关闭、打开）
            item.setMeterID("0000000000000000" + (i + 1));//电表号
            item.setMeterValue("12" + i + ".56kwh");//电表读数
            item.setMeterReportTime(time);//电表上报时间
            item.setDeviceData(getTestDeviceData(nodeID));//模块数据集合

            list.add(item);
        }

        return list;
    }

    private List<NodeMonitorDataOut.DeviceData> getTestDeviceData(int nodeID) {
        List<NodeMonitorDataOut.DeviceData> list = new ArrayList<>();
        NodeMonitorDataOut.DeviceData item;
        for (int i = 0; i < 3; i++) {
            item = new NodeMonitorDataOut.DeviceData();
            item.setDeviceType("DDRC4" + i + "0-FR");//模块类型
            item.setDeviceCode(nodeID + "0x6" + i);//模块代码
            item.setDeviceBox(10 + i);//模块地址
            item.setDeviceStatus(1); //模块状态: 1-在线，O-离线
            item.setDeviceReportTime(DateUtil.getNow()); //模块报告时间
            item.setCH1Value(getTestCHValue());//回路状态对象
            item.setCH2Value(getTestCHValue());//
            item.setCH3Value(getTestCHValue());//
            item.setCH4Value(getTestCHValue());//
            item.setCH5Value(getTestCHValue());//
            item.setCH6Value(getTestCHValue());//
            item.setCH7Value(getTestCHValue());//
            item.setCH8Value(getTestCHValue());//
            item.setCH9Value(getTestCHValue());//
            item.setCH10Value(getTestCHValue());//
            item.setCH11Value(getTestCHValue());//
            item.setCH12Value(getTestCHValue());//

            list.add(item);
        }

        return list;
    }

    private NodeMonitorDataOut.CHValue getTestCHValue() {

        return new NodeMonitorDataOut.CHValue(0, DateUtil.getNow(),
                "221.23V", "220.35V", "230.25V",
                "2.52A", "2.32A", "2.52A",
                "400.00W", "300.00W", "500.00W");
    }

    private JackBoxOut testData() {
        int nodeCount = 3;
        List<JackBoxOut.JackBox> nodeGroup = getTestNoeGroup(nodeCount);

        return new JackBoxOut(nodeCount, nodeGroup);
    }

    private List<JackBoxOut.JackBox> getTestNoeGroup(int nodeCount) {

        List<JackBoxOut.JackBox> list = new ArrayList<>();
        JackBoxOut.JackBox item;
        int index;
        for (int i = 0; i < nodeCount; i++) {
            index = i + 1;
            item = new JackBoxOut.JackBox();
            item.setNodeID(index);              //配电箱编号
            item.setLocal("Local" + "-" + index);               //位置标识
            item.setIPAddress("IPAddress" + "-" + index);           //网关IP地址
            item.setRouteIPAddress("RouteIPAddress" + "-" + index);      //路由器IP地址
            item.setAreaNodeID("AreaNodeID" + "-" + index);          //区域id索引
            item.setPX("PX" + "-" + index);                  //经度
            item.setPY("PY" + "-" + index);                  //纬度
            item.setMeterID("MeterID" + "-" + index);             //电表表号
            item.setMeterType("MeterType" + "-" + index);           //电表品牌或类型
            item.setEngineer("Engineer" + "-" + index);            //维护人员
            item.setTel("Tel" + "-" + index);                 //维护人员电话
            item.setDeviceCount(nodeCount);
            item.setDeviceData(testDevice(nodeCount));

            list.add(item);
        }

        return list;
    }

    private List<JackBoxOut.Device> testDevice(int count) {
        List<JackBoxOut.Device> list = new ArrayList<>();
        JackBoxOut.Device item;
        int index;
        for (int i = 0; i < count; i++) {
            index = i + 1;
            item = new JackBoxOut.Device();
            item.setDeviceType("DeviceType" + "-" + index);
            item.setDeviceCode("DeviceCode" + "-" + index);
            item.setDeviceBox(i);
            item.setCH1Name("CH1Name" + "-" + index);
            item.setCH2Name("CH2Name" + "-" + index);
            item.setCH3Name("CH3Name" + "-" + index);
            item.setCH4Name("CH4Name" + "-" + index);
            item.setCH5Name("CH5Name" + "-" + index);
            item.setCH6Name("CH6Name" + "-" + index);
            item.setCH7Name("CH7Name" + "-" + index);
            item.setCH8Name("CH8Name" + "-" + index);
            item.setCH9Name("CH9Name" + "-" + index);
            item.setCH10Name("CH10Name" + "-" + index);
            item.setCH11Name("CH11Name" + "-" + index);
            item.setCH12Name("CH12Name" + "-" + index);
            item.setCH1Control("CH1Control" + "-" + index);
            item.setCH2Control("CH2Control" + "-" + index);
            item.setCH3Control("CH3Control" + "-" + index);
            item.setCH4Control("CH4Control" + "-" + index);
            item.setCH5Control("CH5Control" + "-" + index);
            item.setCH6Control("CH6Control" + "-" + index);
            item.setCH7Control("CH7Control" + "-" + index);
            item.setCH8Control("CH8Control" + "-" + index);
            item.setCH9Control("CH9Control" + "-" + index);
            item.setCH10Control("CH10Control" + "-" + index);
            item.setCH11Control("CH11Control" + "-" + index);
            item.setCH12Control("CH12Control" + "-" + index);

            list.add(item);
        }

        return list;
    }
}
