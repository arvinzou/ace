package com.huacainfo.ace.woc.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.dao.SiteDao;
import com.huacainfo.ace.woc.dao.TrafficDao;
import com.huacainfo.ace.woc.dao.TrafficSubDao;
import com.huacainfo.ace.woc.dao.VehicleDao;
import com.huacainfo.ace.woc.model.Site;
import com.huacainfo.ace.woc.model.Traffic;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.service.TrafficService;
import com.huacainfo.ace.woc.service.job.TrafficDataManager;
import com.huacainfo.ace.woc.util.VehicleDataUtil;
import com.huacainfo.ace.woc.vo.TrafficQVo;
import com.huacainfo.ace.woc.vo.TrafficSubVo;
import com.huacainfo.ace.woc.vo.TrafficVo;
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
    public MessageResponse updateTrafficStatus(String id, UserProp userProp)
            throws Exception {
        Traffic t = new Traffic();
        t.setId(id);
        t.setStatus("0");
        t.setLastModifyDate(new Date());
        t.setLastModifyUserName(userProp.getName());
        t.setLastModifyUserId(userProp.getUserId());
        this.trafficDao.updateByPrimaryKeySelective(t);
        this.dataBaseLogService.log("变更通行记录", "通行记录", "", t.getId(), t.getId(), userProp);
        return new MessageResponse(0, "变更通行记录完成！");
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
        Vehicle v;
        Site s;
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

            try {
                insertTraffic(t, userProp);
            } catch (Exception e) {
                logger.error("通行记录构建失败：{}", e);
                continue;
            }

            count++;
        }

        return new MessageResponse(0, "数据构建完成！" + count);
    }
}
