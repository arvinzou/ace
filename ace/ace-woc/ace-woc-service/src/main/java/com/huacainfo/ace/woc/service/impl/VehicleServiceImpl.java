package com.huacainfo.ace.woc.service.impl;


import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.woc.dao.VehicleDao;
import com.huacainfo.ace.woc.model.Vehicle;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.VehicleService;
import com.huacainfo.ace.woc.vo.VehicleVo;
import com.huacainfo.ace.woc.vo.VehicleQVo;
@Service("vehicleService")
/**
 * @author: 王恩
 * @version: 2018-03-09
 * @Description:  TODO(人员信息)
 */
public class VehicleServiceImpl implements VehicleService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(人员信息分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<VehicleVo>
	 		* @throws
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
	 *
	    * @Title:insertVehicle
	    * @Description:  TODO(添加人员信息)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse insertVehicle(Vehicle o, UserProp userProp)
			throws Exception {
		o.setId(UUID.randomUUID().toString());
		//o.setId(String.valueOf(new Date().getTime()));
		
		int temp = this.vehicleDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "人员信息名称重复！");
		}
		o.setCreateDate(new Date());
		o.setLastModifyDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.vehicleDao.insert(o);
		this.dataBaseLogService.log("添加人员信息", "人员信息", "", o.getPlateNo(),
				o.getPlateNo(), userProp);
		return new MessageResponse(0, "添加人员信息完成！");
	}
    /**
	 *
	    * @Title:updateVehicle
	    * @Description:  TODO(更新人员信息)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
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
		this.dataBaseLogService.log("变更人员信息", "人员信息", "", o.getPlateNo(),
				o.getPlateNo(), userProp);
		return new MessageResponse(0, "变更人员信息完成！");
	}

    /**
	 *
	    * @Title:selectVehicleByPrimaryKey
	    * @Description:  TODO(获取人员信息)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Vehicle>
	 		* @throws
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
	 *
	    * @Title:deleteVehicleByVehicleId
	    * @Description:  TODO(删除人员信息)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-09
	 */
    @Override
	public MessageResponse deleteVehicleByVehicleId(String id,
			UserProp userProp) throws Exception {
		this.vehicleDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除人员信息", "人员信息", String.valueOf(id),
				String.valueOf(id), "人员信息", userProp);
		return new MessageResponse(0, "人员信息删除完成！");
	}
}
