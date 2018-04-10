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
import com.huacainfo.ace.jxb.dao.ConsultDao;
import com.huacainfo.ace.jxb.model.Consult;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.jxb.service.ConsultService;
import com.huacainfo.ace.jxb.vo.ConsultVo;
import com.huacainfo.ace.jxb.vo.ConsultQVo;
@Service("consultService")
/**
 * @author: lcan
 * @version: 2018-04-08
 * @Description:  TODO(咨询预约)
 */
public class ConsultServiceImpl implements ConsultService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ConsultDao consultDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(咨询预约分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<ConsultVo>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public PageResult<ConsultVo> findConsultList(ConsultQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<ConsultVo> rst = new PageResult<ConsultVo>();
		List<ConsultVo> list = this.consultDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.consultDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertConsult
	    * @Description:  TODO(添加咨询预约)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse insertConsult(Consult o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getName())) {return new MessageResponse(1, "姓名不能为空！");}if (CommonUtils.isBlank(o.getMobile())) {return new MessageResponse(1, "联系方式不能为空！");}if (CommonUtils.isBlank(o.getCategory())) {return new MessageResponse(1, "情感类型不能为空！");}if (CommonUtils.isBlank(o.getDescription())) {return new MessageResponse(1, "问题描述不能为空！");}if (CommonUtils.isBlank(o.getStatus())) {return new MessageResponse(1, "处理状态不能为空！");}
		int temp = this.consultDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "咨询预约名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.consultDao.insert(o);
		this.dataBaseLogService.log("添加咨询预约", "咨询预约", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "添加咨询预约完成！");
	}
    /**
	 *
	    * @Title:updateConsult
	    * @Description:  TODO(更新咨询预约)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse updateConsult(Consult o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {return new MessageResponse(1, "主键不能为空！");}if (CommonUtils.isBlank(o.getName())) {return new MessageResponse(1, "姓名不能为空！");}if (CommonUtils.isBlank(o.getMobile())) {return new MessageResponse(1, "联系方式不能为空！");}if (CommonUtils.isBlank(o.getCategory())) {return new MessageResponse(1, "情感类型不能为空！");}if (CommonUtils.isBlank(o.getDescription())) {return new MessageResponse(1, "问题描述不能为空！");}if (CommonUtils.isBlank(o.getStatus())) {return new MessageResponse(1, "处理状态不能为空！");}
		
		o.setLastModifyDate(new Date());
		o.setLastModifyUserName(userProp.getName());
		o.setLastModifyUserId(userProp.getUserId());
		this.consultDao.updateByPrimaryKey(o);
		this.dataBaseLogService.log("变更咨询预约", "咨询预约", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更咨询预约完成！");
	}

    /**
	 *
	    * @Title:selectConsultByPrimaryKey
	    * @Description:  TODO(获取咨询预约)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Consult>
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public SingleResult<ConsultVo> selectConsultByPrimaryKey(String id) throws Exception {
		SingleResult<ConsultVo> rst = new SingleResult<ConsultVo>();
		rst.setValue(this.consultDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteConsultByConsultId
	    * @Description:  TODO(删除咨询预约)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: lcan
	    * @version: 2018-04-08
	 */
    @Override
	public MessageResponse deleteConsultByConsultId(String id,
			UserProp userProp) throws Exception {
		this.consultDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除咨询预约", "咨询预约", String.valueOf(id),
				String.valueOf(id), "咨询预约", userProp);
		return new MessageResponse(0, "咨询预约删除完成！");
	}
}
