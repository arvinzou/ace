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
import com.huacainfo.ace.woc.dao.CaseDao;
import com.huacainfo.ace.woc.model.Case;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.CaseService;
import com.huacainfo.ace.woc.vo.CaseVo;
import com.huacainfo.ace.woc.vo.CaseQVo;
@Service("caseService")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(案件)
 */
public class CaseServiceImpl implements CaseService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CaseDao caseDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(案件分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CaseVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public PageResult<CaseVo> findCaseList(CaseQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<CaseVo> rst = new PageResult<CaseVo>();
		List<CaseVo> list = this.caseDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.caseDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertCase
	    * @Description:  TODO(添加案件)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse insertCase(Case o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getCaseNo())) {
return new MessageResponse(1, "案件号不能为空！");
}
if (CommonUtils.isBlank(o.getCaseDate())) {
return new MessageResponse(1, "立案日期不能为空！");
}
if (CommonUtils.isBlank(o.getTrafficId())) {
return new MessageResponse(1, "通行记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getVehicleId())) {
return new MessageResponse(1, "车辆记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getDriver())) {
return new MessageResponse(1, "驾驶人记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getParty())) {
return new MessageResponse(1, "当事人记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getPartyType())) {
return new MessageResponse(1, "当事人类型不能为空！");
}
if (CommonUtils.isBlank(o.getChp1())) {
return new MessageResponse(1, "案件处理人1不能为空！");
}
if (CommonUtils.isBlank(o.getLecn())) {
return new MessageResponse(1, "执法证号不能为空！");
}
if (CommonUtils.isBlank(o.getChp2())) {
return new MessageResponse(1, "案件处理人2不能为空！");
}
if (CommonUtils.isBlank(o.getRecorder())) {
return new MessageResponse(1, "记录人不能为空！");
}
if (CommonUtils.isBlank(o.getRecordTime())) {
return new MessageResponse(1, "笔录时间不能为空！");
}
if (CommonUtils.isBlank(o.getAuditDept())) {
return new MessageResponse(1, "审核部门不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}

		int temp = this.caseDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "案件名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.caseDao.insertSelective(o);
		this.dataBaseLogService.log("添加案件", "案件", "", o.getName(), o.getName(), userProp);
		return new MessageResponse(0, "添加案件完成！");
	}
    /**
	 *
	    * @Title:updateCase
	    * @Description:  TODO(更新案件)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse updateCase(Case o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getCaseNo())) {
return new MessageResponse(1, "案件号不能为空！");
}
if (CommonUtils.isBlank(o.getCaseDate())) {
return new MessageResponse(1, "立案日期不能为空！");
}
if (CommonUtils.isBlank(o.getTrafficId())) {
return new MessageResponse(1, "通行记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getVehicleId())) {
return new MessageResponse(1, "车辆记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getDriver())) {
return new MessageResponse(1, "驾驶人记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getParty())) {
return new MessageResponse(1, "当事人记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getPartyType())) {
return new MessageResponse(1, "当事人类型不能为空！");
}
if (CommonUtils.isBlank(o.getChp1())) {
return new MessageResponse(1, "案件处理人1不能为空！");
}
if (CommonUtils.isBlank(o.getLecn())) {
return new MessageResponse(1, "执法证号不能为空！");
}
if (CommonUtils.isBlank(o.getChp2())) {
return new MessageResponse(1, "案件处理人2不能为空！");
}
if (CommonUtils.isBlank(o.getRecorder())) {
return new MessageResponse(1, "记录人不能为空！");
}
if (CommonUtils.isBlank(o.getRecordTime())) {
return new MessageResponse(1, "笔录时间不能为空！");
}
if (CommonUtils.isBlank(o.getAuditDept())) {
return new MessageResponse(1, "审核部门不能为空！");
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
		this.caseDao.updateByPrimaryKeySelective(o);
		this.dataBaseLogService.log("变更案件", "案件", "", o.getName(),
				o.getName(), userProp);
		return new MessageResponse(0, "变更案件完成！");
	}

    /**
	 *
	    * @Title:selectCaseByPrimaryKey
	    * @Description:  TODO(获取案件)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<Case>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public SingleResult<CaseVo> selectCaseByPrimaryKey(String id) throws Exception {
		SingleResult<CaseVo> rst = new SingleResult<CaseVo>();
		rst.setValue(this.caseDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteCaseByCaseId
	    * @Description:  TODO(删除案件)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse deleteCaseByCaseId(String id,
			UserProp userProp) throws Exception {
		this.caseDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除案件", "案件", String.valueOf(id),
				String.valueOf(id), "案件", userProp);
		return new MessageResponse(0, "案件删除完成！");
	}
}
