package com.huacainfo.ace.woc.service.impl;


import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.GUIDUtil;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.dao.PersonDao;
import com.huacainfo.ace.woc.dao.VehicleDao;
import com.huacainfo.ace.woc.model.Person;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.woc.service.VehicleService;
import com.huacainfo.ace.woc.util.VehicleDataUtil;
import com.huacainfo.ace.woc.vo.VehicleQVo;
import com.huacainfo.ace.woc.vo.VehicleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("vehicleService")
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description: TODO(车辆信息)
 */
public class VehicleServiceImpl implements VehicleService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonDao personDao;
    @Autowired
    private VehicleDao vehicleDao;
    @Autowired
    private DataBaseLogService dataBaseLogService;

    /**
     * @throws
     * @Title:find!{bean.name}List
     * @Description: TODO(车辆信息分页查询)
     * @param: @param condition
     * @param: @param start
     * @param: @param limit
     * @param: @param orderBy
     * @param: @throws Exception
     * @return: PageResult<VehicleVo>
     * @author: 王恩
     * @version: 2018-03-09
     */
    @Override
    public PageResult<VehicleVo> findVehicleList(VehicleQVo condition, int start,
                                                 int limit, String orderBy) throws Exception {
        PageResult<VehicleVo> rst = new PageResult<VehicleVo>();
        List<VehicleVo> list = this.vehicleDao.findList(condition,
                start, start + limit, orderBy);
        rst.setRows(list);
        if (start <= 1) {
            int allRows = this.vehicleDao.findCount(condition);
            rst.setTotal(allRows);
        }
        return rst;
    }

    /**
     * @throws
     * @Title:insertVehicle
     * @Description: TODO(添加车辆信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-09
     */
    @Override
    public MessageResponse insertVehicle(Vehicle o, UserProp userProp)
            throws Exception {
        o.setId(GUIDUtil.getGUID());
        int temp = this.vehicleDao.isExit(o);
        if (temp > 0) {
            return new MessageResponse(1, "车辆信息名称重复！");
        }
        o.setCreateDate(new Date());
        o.setLastModifyDate(new Date());
        o.setStatus("1");
        o.setCreateUserName(userProp.getName());
        o.setCreateUserId(userProp.getUserId());
        this.vehicleDao.insertSelective(o);
        this.dataBaseLogService.log("添加车辆信息", "车辆信息", "", o.getPlateNo(),
                o.getPlateNo(), userProp);
        return new MessageResponse(0, "添加车辆信息完成！");
    }

    /**
     * @throws
     * @Title:updateVehicle
     * @Description: TODO(更新车辆信息)
     * @param: @param o
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-09
     */
    @Override
    public MessageResponse updateVehicle(Vehicle o, UserProp userProp)
            throws Exception {
        o.setLastModifyDate(new Date());
        o.setLastModifyUserName(userProp.getName());
        o.setLastModifyUserId(userProp.getUserId());
        this.vehicleDao.updateByPrimaryKeySelective(o);
        this.dataBaseLogService.log("变更车辆信息", "车辆信息", "", o.getPlateNo(),
                o.getPlateNo(), userProp);
        return new MessageResponse(0, "变更车辆信息完成！");
    }

    /**
     * @throws
     * @Title:selectVehicleByPrimaryKey
     * @Description: TODO(获取车辆信息)
     * @param: @param id
     * @param: @throws Exception
     * @return: SingleResult<Vehicle>
     * @author: 王恩
     * @version: 2018-03-09
     */
    @Override
    public SingleResult<VehicleVo> selectVehicleByPrimaryKey(String id) throws Exception {
        SingleResult<VehicleVo> rst = new SingleResult<VehicleVo>();
        rst.setValue(this.vehicleDao.selectByPrimaryKey(id));
        return rst;
    }

    /**
     * @throws
     * @Title:deleteVehicleByVehicleId
     * @Description: TODO(删除车辆信息)
     * @param: @param id
     * @param: @param userProp
     * @param: @throws Exception
     * @return: MessageResponse
     * @author: 王恩
     * @version: 2018-03-09
     */
    @Override
    public MessageResponse deleteVehicleByVehicleId(String id,
                                                    UserProp userProp) throws Exception {
        this.vehicleDao.deleteByPrimaryKey(id);
        this.dataBaseLogService.log("删除车辆信息", "车辆信息", String.valueOf(id),
                String.valueOf(id), "车辆信息", userProp);
        return new MessageResponse(0, "车辆信息删除完成！");
    }

    /**
     * 根据关键词，查询车辆信息
     *
     * @param keyWord
     * @return
     */
    @Override
    public Map<String, Object> selectListByKeyWord(String keyWord) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("keyWord", keyWord);

        List<VehicleVo> list = vehicleDao.selectListByKeyWord(params);
        Map<String, Object> rtn = new HashMap<>();
        rtn.put("total", list.size());
        rtn.put("rows", list);
        return rtn;
    }

    @Override
    public MessageResponse createData(String dataNum) {
        Vehicle o;
        UserProp userProp = new UserProp();
        userProp.setName("system");
        userProp.setUserId("88888888");
        int count = 0;
        Map<String, Object> axle;

        List<Person> personList = personDao.selectAll();
        Person p;//= personList.get(new Random().nextInt(personList.size()));
        for (int i = 0; i < Integer.valueOf(dataNum); i++) {
            p = personList.get(new Random().nextInt(personList.size()));
            o = new Vehicle();
            o.setPlateNo(VehicleDataUtil.getRandomPlateNo());
            o.setVehicleColor(VehicleDataUtil.getRandomCarColor());
            o.setModel(VehicleDataUtil.getRandomCarBrand());
            axle = VehicleDataUtil.getRandomAxle();
            o.setAxleCount((Integer) axle.get("axleCount"));
            o.setVehicleType((String) axle.get("vehicleType"));
            o.setTotalMass((BigDecimal) axle.get("totalMass"));
            o.setUnladedMass((BigDecimal) axle.get("unladedMass"));
            o.setApprovedMass((BigDecimal) axle.get("approvedMass"));
            o.setTractionMass((BigDecimal) axle.get("tractionMass"));
            o.setContainerInsideSize("");
            o.setOwnerId(p.getId());
            try {
                insertVehicle(o, userProp);
            } catch (Exception e) {
                continue;
            }
            count++;
        }

        return new MessageResponse(0, "虚拟数据构建完成！" + count);
    }


}
