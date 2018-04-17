package com.huacainfo.ace.woc.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.constant.TrafficConstant;
import com.huacainfo.ace.woc.dao.SiteDao;
import com.huacainfo.ace.woc.dao.TrafficDao;
import com.huacainfo.ace.woc.dao.TrafficSubDao;
import com.huacainfo.ace.woc.dao.VehicleDao;
import com.huacainfo.ace.woc.model.*;
import com.huacainfo.ace.woc.service.PersonService;
import com.huacainfo.ace.woc.service.TrafficIllegalService;
import com.huacainfo.ace.woc.service.TrafficService;
import com.huacainfo.ace.woc.service.TrafficSubService;
import com.huacainfo.ace.woc.service.job.TrafficDataManager;
import com.huacainfo.ace.woc.util.VehicleDataUtil;
import com.huacainfo.ace.woc.vo.TrafficQVo;
import com.huacainfo.ace.woc.vo.TrafficSubVo;
import com.huacainfo.ace.woc.vo.TrafficVo;
import com.huacainfo.ace.woc.wechat.api.MessageSendApi;
import com.huacainfo.ace.woc.wechat.entity.TemplateData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("trafficService")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(通行记录)
 */
public class TrafficServiceImpl implements TrafficService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonService personService;
    @Autowired
    private TrafficIllegalService trafficIllegalService;
    @Autowired
    private TrafficSubService trafficSubService;
    @Autowired
    private SiteDao siteDao;
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private TrafficDao trafficDao;
    @Autowired
    private TrafficSubDao trafficSubDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(通行记录分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<TrafficVo>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public PageResult<TrafficVo> findTrafficList(TrafficQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<TrafficVo> rst = new PageResult<TrafficVo>();
        List<TrafficVo> list = this.trafficDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.trafficDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertTraffic
     * @Description: TODO(添加通行记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse insertTraffic(Traffic o, UserProp userProp)
            throws Exception {


        if (CommonUtils.isBlank(o.getInspectTime())) {
            return new MessageResponse(1, "检查时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getLocale())) {
            return new MessageResponse(1, "地点不能为空！");
        }
        if (CommonUtils.isBlank(o.getPlateNo())) {
            return new MessageResponse(1, "车牌号不能为空！");
        }
        if (CommonUtils.isBlank(o.getDirection())) {
            return new MessageResponse(1, "方向不能为空！");
        }
        if (CommonUtils.isBlank(o.getAxleCount())) {
            return new MessageResponse(1, "轴数不能为空！");
        }
        if (CommonUtils.isBlank(o.getTotalMass())) {
            return new MessageResponse(1, "总重量不能为空！");
        }
        if (CommonUtils.isBlank(o.getOverMass())) {
            return new MessageResponse(1, "超限不能为空！");
        }
        if (CommonUtils.isBlank(o.getOverRate())) {
            return new MessageResponse(1, "超限率不能为空！");
        }

//        int temp = this.trafficDao.isExit(o);
//        if (temp > 0) {
//            return new MessageResponse(1, "通行记录名称重复！");
//        }

        o.setId(GUIDUtil.getGUID());
        o.setLastModifyDate(DateUtil.getNowDate());
        o.setCreateDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.trafficDao.insertSelective(o);
        this.dataBaseLogService.log("添加通行记录", "通行记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "添加通行记录完成！");
    }

    /**
     * @throws
     * @Title:updateTraffic
     * @Description: TODO(更新通行记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse updateTraffic(Traffic o, UserProp userProp)
            throws Exception {
        if (CommonUtils.isBlank(o.getId())) {
            return new MessageResponse(1, "主键不能为空！");
        }
        if (CommonUtils.isBlank(o.getInspectTime())) {
            return new MessageResponse(1, "检查时间不能为空！");
        }
        if (CommonUtils.isBlank(o.getLocale())) {
            return new MessageResponse(1, "地点不能为空！");
        }
        if (CommonUtils.isBlank(o.getPlateNo())) {
            return new MessageResponse(1, "车牌号不能为空！");
        }
        if (CommonUtils.isBlank(o.getDirection())) {
            return new MessageResponse(1, "方向不能为空！");
        }
        if (CommonUtils.isBlank(o.getAxleCount())) {
            return new MessageResponse(1, "轴数不能为空！");
        }
        if (CommonUtils.isBlank(o.getTotalMass())) {
            return new MessageResponse(1, "总重量不能为空！");
        }
        if (CommonUtils.isBlank(o.getOverMass())) {
            return new MessageResponse(1, "超限不能为空！");
        }
        if (CommonUtils.isBlank(o.getOverRate())) {
            return new MessageResponse(1, "超限率不能为空！");
        }

        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.trafficDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更通行记录", "通行记录", "", o.getId(),
                o.getId(), userProp);
        return new MessageResponse(0, "变更通行记录完成！");
    }

    /**
     * @throws
     * @Title:updateTraffic
     * @Description: TODO(更新通行记录)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse updateTrafficStatus(String id, UserProp userProp) throws Exception {
        Traffic traffic = trafficDao.selectByPrimaryKey(id);
        if (null == traffic) {
            return new MessageResponse(0, "通行记录不存在");
        }

        traffic.setStatus("0");
        traffic.setLastModifyDate(new Date());
        traffic.setLastModifyUserName(userProp.getName());
        traffic.setLastModifyUserId(userProp.getUserId());
        this.trafficDao.updateByPrimaryKeySelective(traffic);
        this.dataBaseLogService.log("变更通行记录", "通行记录", "", traffic.getId(), traffic.getId(), userProp);

        TrafficIllegal tt = new TrafficIllegal();
        tt.setTrafficId(id);
        trafficIllegalService.insertTrafficIllegalII(tt, userProp);

        //违章记录通知
        sendIllegalTrafficMessage(traffic);

        return new MessageResponse(0, "变更通行记录完成！");
    }

    /**
     * 发送微信违章通知消息
     *
     * @param traffic 通行记录信息
     */
    private void sendIllegalTrafficMessage(Traffic traffic) {
        int sendFlag = 1;
        if (1 == sendFlag) {
            Person person = personService.findVehicleOwner(traffic.getPlateNo());
            if (null != person && CommonUtils.isNotBlank(person.getPhoneNumber())) {

                Map<String, Object> weChatInfo = personService.findWeChatInfo(person.getPhoneNumber());
                if (null == weChatInfo || CommonUtils.isBlank(weChatInfo.get("accessToken"))) {
                    return;
                }
                String openId = (String) weChatInfo.get("openid");//"oFvIjw9bgtJmgvqVv0XIayPsh2QI";
                String templateId = "kdRZ39sBzGCXaXSz20-s3x8WAV1cyWxeKoaVaspl3qE";
                String url = "www.qq.com";

                Map<String, String> params = new HashMap<>();
                params.put("first", "违章记录通知");
                params.put("keyword1", person.getName());
                params.put("keyword2", traffic.getPlateNo());
                params.put("keyword3", DateUtil.format(traffic.getInspectTime()));
                params.put("keyword4", traffic.getLocale());
                params.put("remark", "您有的车辆新违章，请及时查收!");
                TemplateData data = MessageSendApi.buildTemplateData(openId, templateId, url, params);

                String sendResult = MessageSendApi.sendTemplate((String) weChatInfo.get("accessToken"), data);
                logger.info("通行记录[" + traffic.getId() + "],发送通知结果：" + sendResult);
            }
        }
    }

    /**
     * @throws
     * @Title:selectTrafficByPrimaryKey
     * @Description: TODO(获取通行记录)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Traffic>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public SingleResult<TrafficVo> selectTrafficByPrimaryKey(String id) throws Exception {
        SingleResult<TrafficVo> rst = new SingleResult<TrafficVo>();
        TrafficVo t = this.trafficDao.selectByPrimaryKeyMsg(id);
        List<TrafficSubVo> l = trafficSubDao.findListByTrafficId(t.getId());
        t.setTrafficSubVo(l);
        rst.setValue(t);
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTrafficByTrafficId
     * @Description: TODO(删除通行记录)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public MessageResponse deleteTrafficByTrafficId(String id,
                                                    UserProp userProp) throws Exception {
        this.trafficDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除通行记录", "通行记录", String.valueOf(id),
                String.valueOf(id), "通行记录", userProp);
        return new MessageResponse(0, "通行记录删除完成！");
    }

    @Override
    public Map<String, Object> selectListByKeyWord(String keyWord, String id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("keyWord", keyWord);
        if (CommonUtils.isNotBlank(id)) {
            params.put("id", id);
        }

        List<TrafficVo> list = trafficDao.selectListByKeyWord(params);
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("total", list.size());
        rtn.put("rows", list);
        return rtn;
    }

    /**
     * 创建模拟数据
     *
     * @param date     日期时间 yyyy-MM-dd hh:mm:ss
     * @param num      一次构建的数据条数
     * @param userProp 当前登录用户信息
     * @return 处理结果
     */
    @Override
    public MessageResponse createData(String date, String num, UserProp userProp) {
        date = CommonUtils.isBlank(date) ? DateUtil.getNow() : date;
        num = CommonUtils.isBlank(num) ? "1" : num;

        List<Vehicle> vehicleList = vehicleDao.selectAll();
        List<Site> siteList = siteDao.selectAll();
        Traffic t;
        TrafficSub sub;
        Vehicle v;
        Site s;
        String trafficId;
        int count = 0;
        for (int i = 0; i < Integer.valueOf(num); i++) {
            v = vehicleList.get(new Random().nextInt(vehicleList.size()));
            s = siteList.get(new Random().nextInt(siteList.size()));
            t = new Traffic();
            t.setSiteId(s.getId());
            t.setInspectTime(DateUtil.toDate(date));
            t.setLocale(s.getSiteName());
            t.setPlateNo(v.getPlateNo());
            t.setDirection(VehicleDataUtil.getRandomData(TrafficDataManager.DIRECTION));
            t.setAxleCount(v.getAxleCount());
            int rate = TrafficDataManager.getRandomRate();
            BigDecimal totalMass = TrafficDataManager.getCheckTotalMass(rate, v);
            t.setTotalMass(totalMass);
            t.setOverMass(totalMass.subtract(v.getTotalMass()));
            t.setOverRate(new BigDecimal(rate).divide(new BigDecimal(100)));

            trafficId = GUIDUtil.getGUID();
            t.setId(trafficId);
            t.setLastModifyDate(DateUtil.getNowDate());
            t.setCreateDate(new Date());
            t.setStatus("1");
            t.setCreateUserName(userProp.getName());
            t.setCreateUserId(userProp.getUserId());

            try {
                int iCount = trafficDao.insertSelective(t);
                if (iCount > 0) {
                    for (int j = 0; j < 4; j++) {
                        sub = new TrafficSub();
                        sub.setId(GUIDUtil.getGUID());
                        sub.setTrafficId(trafficId);
                        sub.setPlateNo(t.getPlateNo());
                        sub.setInspectTime(DateUtil.getNowDate());
                        if (0 == j) {
                            sub.setCategory(TrafficConstant.TRAFFIC_SUB_CATEGORY_VEHICLE_FRONT);
                            sub.setFileUrl("group1/M00/00/25/i-AA41rUENaAdLkUAAECSU7ktj4896.jpg?filename=1.jpg");
                        }
                        if (1 == j) {
                            sub.setCategory(TrafficConstant.TRAFFIC_SUB_CATEGORY_VEHICLE_BACK);
                            sub.setFileUrl("group1/M00/00/25/i-AA41rUEO2AVt7tAABLCiTGGkQ380.jpg?filename=2.jpg");
                        }
                        if (2 == j) {
                            sub.setCategory(TrafficConstant.TRAFFIC_SUB_CATEGORY_VEHICLE_SIDE);
                            sub.setFileUrl("group1/M00/00/25/i-AA41rUEPmAemkrAABjWHjM5XM636.jpg?filename=3.jpg");
                        }
                        if (3 == j) {
                            sub.setCategory(TrafficConstant.TRAFFIC_SUB_CATEGORY_VEHICLE_WHOLE);
                            sub.setFileUrl("group1/M00/00/25/i-AA41rUEQaAbkTSAACHFey8R6g949.jpg?filename=4.jpg");
                        }
                        sub.setCreateDate(new Date());
                        sub.setLastModifyDate(new Date());
                        sub.setStatus("1");
                        sub.setCreateUserName(userProp.getName());
                        sub.setCreateUserId(userProp.getUserId());

                        trafficSubDao.insertSelective(sub);
                    }
                }
            } catch (Exception e) {
                logger.error("通行记录构建失败：{}", e);
                continue;
            }
            count++;
        }

        return new MessageResponse(0, "数据构建完成！" + count);
    }
}
