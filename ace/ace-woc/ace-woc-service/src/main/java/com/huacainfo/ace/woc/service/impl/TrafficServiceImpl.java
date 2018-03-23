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
import com.huacainfo.ace.woc.dao.TrafficDao;
import com.huacainfo.ace.woc.model.Traffic;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.TrafficService;
import com.huacainfo.ace.woc.vo.TrafficVo;
import com.huacainfo.ace.woc.vo.TrafficQVo;
@Service("trafficService")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(通行记录)
 */
public class TrafficServiceImpl implements TrafficService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrafficDao trafficDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	 * @Description: TODO(通行记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficVo>
	 		* @throws
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
	 *
	    * @Title:insertTraffic
	 * @Description: TODO(添加通行记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse insertTraffic(Traffic o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
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
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}

		int temp = this.trafficDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "通行记录名称重复！");
		}
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
	 *
	    * @Title:updateTraffic
	 * @Description: TODO(更新通行记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
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
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
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
	 *
	    * @Title:selectTrafficByPrimaryKey
	 * @Description: TODO(获取通行记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Traffic>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public SingleResult<TrafficVo> selectTrafficByPrimaryKey(String id) throws Exception {
		SingleResult<TrafficVo> rst = new SingleResult<TrafficVo>();
		rst.setValue(this.trafficDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteTrafficByTrafficId
	 * @Description: TODO(删除通行记录)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
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
}
