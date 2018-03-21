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
import com.huacainfo.ace.woc.dao.TrafficIllegalCancelDao;
import com.huacainfo.ace.woc.model.TrafficIllegalCancel;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.TrafficIllegalCancelService;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelVo;
import com.huacainfo.ace.woc.vo.TrafficIllegalCancelQVo;
@Service("trafficIllegalCancelService")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description: TODO(违章撤销记录)
 */
public class TrafficIllegalCancelServiceImpl implements TrafficIllegalCancelService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TrafficIllegalCancelDao trafficIllegalCancelDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	 * @Description: TODO(违章撤销记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<TrafficIllegalCancelVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public PageResult<TrafficIllegalCancelVo> findTrafficIllegalCancelList(TrafficIllegalCancelQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<TrafficIllegalCancelVo> rst = new PageResult<TrafficIllegalCancelVo>();
		List<TrafficIllegalCancelVo> list = this.trafficIllegalCancelDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.trafficIllegalCancelDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertTrafficIllegalCancel
	 * @Description: TODO(添加违章撤销记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse insertTrafficIllegalCancel(TrafficIllegalCancel o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getTrafficId())) {
return new MessageResponse(1, "通行记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getCancelTime())) {
return new MessageResponse(1, "撤销时间不能为空！");
}
if (CommonUtils.isBlank(o.getCancelor())) {
return new MessageResponse(1, "撤销人不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}

		int temp = this.trafficIllegalCancelDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "违章撤销记录名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.trafficIllegalCancelDao.insertSelective(o);
		this.dataBaseLogService.log("添加违章撤销记录", "违章撤销记录", "", o.getTrafficId(),
				o.getTrafficId(), userProp);
		return new MessageResponse(0, "添加违章撤销记录完成！");
	}
    /**
	 *
	    * @Title:updateTrafficIllegalCancel
	 * @Description: TODO(更新违章撤销记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse updateTrafficIllegalCancel(TrafficIllegalCancel o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getTrafficId())) {
return new MessageResponse(1, "通行记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getCancelTime())) {
return new MessageResponse(1, "撤销时间不能为空！");
}
if (CommonUtils.isBlank(o.getCancelor())) {
return new MessageResponse(1, "撤销人不能为空！");
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
		this.trafficIllegalCancelDao.updateByPrimaryKeySelective(o);
		this.dataBaseLogService.log("变更违章撤销记录", "违章撤销记录", "", o.getTrafficId(),
				o.getTrafficId(), userProp);
		return new MessageResponse(0, "变更违章撤销记录完成！");
	}

    /**
	 *
	    * @Title:selectTrafficIllegalCancelByPrimaryKey
	 * @Description: TODO(获取违章撤销记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<TrafficIllegalCancel>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public SingleResult<TrafficIllegalCancelVo> selectTrafficIllegalCancelByPrimaryKey(String id) throws Exception {
		SingleResult<TrafficIllegalCancelVo> rst = new SingleResult<TrafficIllegalCancelVo>();
		rst.setValue(this.trafficIllegalCancelDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteTrafficIllegalCancelByTrafficIllegalCancelId
	 * @Description: TODO(删除违章撤销记录)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse deleteTrafficIllegalCancelByTrafficIllegalCancelId(String id,
			UserProp userProp) throws Exception {
		this.trafficIllegalCancelDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除违章撤销记录", "违章撤销记录", String.valueOf(id),
				String.valueOf(id), "违章撤销记录", userProp);
		return new MessageResponse(0, "违章撤销记录删除完成！");
	}
}
