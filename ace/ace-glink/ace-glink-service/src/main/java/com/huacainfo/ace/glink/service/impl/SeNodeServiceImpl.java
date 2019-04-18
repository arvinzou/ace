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
import com.huacainfo.ace.glink.dao.SeNodeDao;
import com.huacainfo.ace.glink.dao.SeNodeDeviceDao;
import com.huacainfo.ace.glink.model.SeNode;
import com.huacainfo.ace.glink.model.SeNodeDevice;
import com.huacainfo.ace.glink.service.SeNodeService;
import com.huacainfo.ace.glink.vo.SeNodeQVo;
import com.huacainfo.ace.glink.vo.SeNodeVo;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public static void main(String[] args) {

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
