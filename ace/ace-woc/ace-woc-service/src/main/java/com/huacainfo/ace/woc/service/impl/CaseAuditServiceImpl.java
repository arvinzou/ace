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
import com.huacainfo.ace.woc.dao.CaseAuditDao;
import com.huacainfo.ace.woc.model.CaseAudit;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.woc.service.CaseAuditService;
import com.huacainfo.ace.woc.vo.CaseAuditVo;
import com.huacainfo.ace.woc.vo.CaseAuditQVo;
@Service("caseAuditService")
/**
 * @author: 王恩
 * @version: 2018-03-21
 * @Description:  TODO(案件审核记录)
 */
public class CaseAuditServiceImpl implements CaseAuditService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CaseAuditDao caseAuditDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;

    /**
	 *
	    * @Title:find!{bean.name}List
	    * @Description:  TODO(案件审核记录分页查询)
	 		* @param:        @param condition
	 		* @param:        @param start
	 		* @param:        @param limit
	 		* @param:        @param orderBy
	 		* @param:        @throws Exception
	 		* @return:       PageResult<CaseAuditVo>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public PageResult<CaseAuditVo> findCaseAuditList(CaseAuditQVo condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<CaseAuditVo> rst = new PageResult<CaseAuditVo>();
		List<CaseAuditVo> list = this.caseAuditDao.findList(condition,
				start, start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.caseAuditDao.findCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
    /**
	 *
	    * @Title:insertCaseAudit
	    * @Description:  TODO(添加案件审核记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse insertCaseAudit(CaseAudit o, UserProp userProp)
			throws Exception {
		o.setId(GUIDUtil.getGUID());
		//o.setId(String.valueOf(new Date().getTime()));
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getCaseId())) {
return new MessageResponse(1, "案件记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getAuditTime())) {
return new MessageResponse(1, "审核时间不能为空！");
}
if (CommonUtils.isBlank(o.getAuditor())) {
return new MessageResponse(1, "审核人不能为空！");
}
if (CommonUtils.isBlank(o.getAuditResult())) {
return new MessageResponse(1, "审核结果不能为空！");
}
if (CommonUtils.isBlank(o.getStatus())) {
return new MessageResponse(1, "状态不能为空！");
}
if (CommonUtils.isBlank(o.getLastModifyDate())) {
return new MessageResponse(1, "最后更新时间不能为空！");
}

		int temp = this.caseAuditDao.isExit(o);
		if (temp > 0) {
			return new MessageResponse(1, "案件审核记录名称重复！");
		}
		o.setCreateDate(new Date());
		o.setStatus("1");
		o.setCreateUserName(userProp.getName());
		o.setCreateUserId(userProp.getUserId());
		this.caseAuditDao.insertSelective(o);
		this.dataBaseLogService.log("添加案件审核记录", "案件审核记录", "", o.getCaseId(),
				o.getCaseId(), userProp);
		return new MessageResponse(0, "添加案件审核记录完成！");
	}
    /**
	 *
	    * @Title:updateCaseAudit
	    * @Description:  TODO(更新案件审核记录)
	 		* @param:        @param o
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse updateCaseAudit(CaseAudit o, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(o.getId())) {
return new MessageResponse(1, "主键不能为空！");
}
if (CommonUtils.isBlank(o.getCaseId())) {
return new MessageResponse(1, "案件记录主键不能为空！");
}
if (CommonUtils.isBlank(o.getAuditTime())) {
return new MessageResponse(1, "审核时间不能为空！");
}
if (CommonUtils.isBlank(o.getAuditor())) {
return new MessageResponse(1, "审核人不能为空！");
}
if (CommonUtils.isBlank(o.getAuditResult())) {
return new MessageResponse(1, "审核结果不能为空！");
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
		this.caseAuditDao.updateByPrimaryKeySelective(o);
		this.dataBaseLogService.log("变更案件审核记录", "案件审核记录", "", o.getCaseId(),
				o.getCaseId(), userProp);
		return new MessageResponse(0, "变更案件审核记录完成！");
	}

    /**
	 *
	    * @Title:selectCaseAuditByPrimaryKey
	    * @Description:  TODO(获取案件审核记录)
	 		* @param:        @param id
	 		* @param:        @throws Exception
	 		* @return:       SingleResult<CaseAudit>
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public SingleResult<CaseAuditVo> selectCaseAuditByPrimaryKey(String id) throws Exception {
		SingleResult<CaseAuditVo> rst = new SingleResult<CaseAuditVo>();
		rst.setValue(this.caseAuditDao.selectByPrimaryKey(id));
		return rst;
	}
    /**
	 *
	    * @Title:deleteCaseAuditByCaseAuditId
	    * @Description:  TODO(删除案件审核记录)
	 		* @param:        @param id
	 		* @param:        @param userProp
	 		* @param:        @throws Exception
	 		* @return:       MessageResponse
	 		* @throws
	    * @author: 王恩
	    * @version: 2018-03-21
	 */
    @Override
	public MessageResponse deleteCaseAuditByCaseAuditId(String id,
			UserProp userProp) throws Exception {
		this.caseAuditDao.deleteByPrimaryKey(id);
		this.dataBaseLogService.log("删除案件审核记录", "案件审核记录", String.valueOf(id),
				String.valueOf(id), "案件审核记录", userProp);
		return new MessageResponse(0, "案件审核记录删除完成！");
	}
}
