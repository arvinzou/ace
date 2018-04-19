package com.huacainfo.ace.woc.service.impl;


import com.huacainfo.ace.common.constant.ResultCode;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DateUtil;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.dao.TrafficDao;
import com.huacainfo.ace.woc.dao.TrafficIllegalDao;
import com.huacainfo.ace.woc.dao.VehicleDao;
import com.huacainfo.ace.woc.model.Cases;
import com.huacainfo.ace.woc.model.Traffic;
import com.huacainfo.ace.woc.model.TrafficIllegal;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.service.CasesService;
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
    private VehicleDao vehicleDao;
    @Autowired
    private CasesService casesService;
    @Autowired
    private TrafficDao trafficDao;
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
     * @param: @param ids
     * @param: @throws Exception
     * @return: SingleResult<TrafficIllegal>
     * @author: 王恩
     * @version: 2018-03-21
     */
    @Override
    public SingleResult<TrafficIllegalVo> selectTrafficIllegalByPrimaryKey(String id) throws Exception {
        SingleResult<TrafficIllegalVo> rst = new SingleResult<TrafficIllegalVo>();
        rst.setValue(this.trafficIllegalDao.selectByPrimaryKeyVo(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteTrafficIllegalByTrafficIllegalId
     * @Description: TODO(删除通行违章记录)
     * @param: @param ids
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

    /**
     * 违章记录转为案件
     *
     * @param ids     id1,id2,ide3    woc.traffic_illegal.ids
     * @param curUser 登录管理用户信息
     * @return
     */
    @Override
    public MessageResponse convertToCases(String ids, UserProp curUser) throws Exception {
        if (CommonUtils.isBlank(ids)) {
            return new MessageResponse(ResultCode.FAIL, "无效违章记录主键");
        }
        String[] idArray = ids.split(",");
        for (String id : idArray) {
            TrafficIllegal trafficIllegal = trafficIllegalDao.selectByPrimaryKey(id);
            if (null == trafficIllegal || "2".equals(trafficIllegal.getStatus())) {
                continue;
//                return new MessageResponse(ResultCode.FAIL, "无效相关违章记录资料");
            }
            Traffic traffic = trafficDao.selectByPrimaryKey(trafficIllegal.getTrafficId());
            if (null == traffic) {
                continue;
//                return new MessageResponse(ResultCode.FAIL, "通行记录资料丢失");
            }
            Vehicle vehicle = vehicleDao.selectByPlatNo(traffic.getPlateNo());
            if (null == vehicle) {
                continue;
//                return new MessageResponse(ResultCode.FAIL, "车辆资料丢失");
            }
            String personId = CommonUtils.isNotEmpty(vehicle.getOwnerId()) ?
                    vehicle.getOwnerId() : vehicle.getOwnerCompanyId();
            //案件资料
            Cases cases = new Cases();
            cases.setCaseNo("CN" + System.currentTimeMillis());
            cases.setCaseDate(DateUtil.getNowDate());
            cases.setTrafficId(traffic.getId());
            cases.setVehicleId(vehicle.getId());
            cases.setDriver(personId);
            cases.setParty(personId);
            cases.setPartyType("1");//暂时未定义具体含义
            cases.setChp1(personId);
            cases.setLecn("ZF" + System.currentTimeMillis());
            cases.setChp2(personId);
            cases.setRecorder(personId);
            cases.setRecordTime(DateUtil.getNowDate());
            cases.setAuditDept("00030005");//暂定为此

            MessageResponse response = casesService.insertCases(cases, curUser);
            //插入失败
            if (response.getStatus() == ResultCode.FAIL) {
                continue;
//                return response;
            }
            trafficIllegal.setStatus("2");//已转为案件
            updateTrafficIllegal(trafficIllegal, curUser);
        }

        return new MessageResponse(ResultCode.SUCCESS, "转录案件成功！");
    }
}
