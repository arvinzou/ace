package com.huacainfo.ace.portal.service.impl;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.ListResult;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult ;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.DepartmentDao;
import com.huacainfo.ace.portal.model.Department;
import com.huacainfo.ace.portal.service.CacheService;
import com.huacainfo.ace.portal.service.ConfigService;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.DepartmentService;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("deprecation")
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	
	@Autowired
	private KafkaProducerService kafkaProducerService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ConfigService configService;
	@Override
	public PageResult<DepartmentVo> findDepartmentList(Department condition,
			int start, int limit, String orderBy) throws Exception {
		PageResult<DepartmentVo> rst = new PageResult<DepartmentVo>();
		List<DepartmentVo> list = this.departmentDao.findDepartmentList(
				condition, start, limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.departmentDao.findDepartmentCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
	@Override
	public MessageResponse insertDepartment(Department o, UserProp userProp)
			throws Exception {

		logger.debug(o.toString());
		o.setRegDate(new Date());
		o.setCreateTime(new Date());
		o.setStatus("1");
		o.setSyid(userProp.getActiveSyId());
		if (CommonUtils.isBlank(o.getParentDepartmentId())) {
			return new MessageResponse(1, "所属协会不能为空！");
		}
		if (CommonUtils.isBlank(o.getDepartmentName())) {
			return new MessageResponse(1, "企业名称不能为空！");
		}
		/*if (CommonUtils.isBlank(o.getShortName())) {
			return new MessageResponse(1, "简称不能为空！");
		}
		if (CommonUtils.isBlank(o.getCategory())) {
			return new MessageResponse(1, "类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属地区不能为空！");
		}
		if (CommonUtils.isBlank(o.getContactName())) {
			return new MessageResponse(1, "联系人姓名不能为空！");
		}

		if (CommonUtils.isBlank(o.getContactEmail())) {
			return new MessageResponse(1, "联系人电子邮箱不能为空！");
		}
		if (CommonUtils.isBlank(o.getLegalPersonName())) {
			return new MessageResponse(1, "法定联系人不能为空！");
		}
		if (CommonUtils.isBlank(o.getLegalPersonIdType())) {
			return new MessageResponse(1, "法人证件类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getLegalPersonIdNo())) {
			return new MessageResponse(1, "法定人证件号不能为空！");
		}
		if (CommonUtils.isBlank(o.getRegDate())) {
			return new MessageResponse(1, "注册时间不能为空！");
		}
		if (CommonUtils.isBlank(o.getNature())) {
			return new MessageResponse(1, "经济性质不能为空！");
		}
		if (CommonUtils.isBlank(o.getBussLicenseNo())) {
			return new MessageResponse(1, "营业执照号不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态不能为空！");
		}
		if (CommonUtils.isBlank(o.getType())) {
			return new MessageResponse(1, "企业类型不能为空！");
		}
		if (CommonUtils.isBlank(o.getCreateTime())) {
			return new MessageResponse(1, "创建时间不能为空！");
		}
		t = this.departmentDao.isExitEmail(o.getContactEmail());
		if (t > 0) {
			return new MessageResponse(1, "已存在的联系人邮箱！");
		}
		t = this.departmentDao.isExitBussLicenseNo(o);
		if (t > 0) {
			return new MessageResponse(1, "已存在的营业执照号！");
		}
		*/
		int t = this.departmentDao.isExit(o);
		if (t > 0) {
			//return new MessageResponse(1, "已存在的部门名称！");
		}

		this.departmentDao.insertDepartment(o);
		/*String password=CommonUtils.genRandomNum(6);
		String seat=UUID.randomUUID().toString();
		Users users=new Users();
		users.setAccount(o.getContactEmail());
		users.setAreaCode(o.getAreaCode());
		users.setBirthday(null);
		users.setCreateTime(new Date());
		users.setDepartmentId(o.getDepartmentId());
		users.setIdCard(null);
		users.setMobile(o.getContactMobile());
		users.setName(o.getContactName());
		users.setPassword(CommonUtils.getMd5(password));
		users.setSex("1");
		users.setStauts("1");
		users.setUserLevel("1");
		users.setEmail(o.getContactEmail());
		users.setSeat(seat);
		this.departmentDao.insertUsersAndRole(users);
		configService.insertDeptConfig(o.getDepartmentId());
		Map<String,Object> model=new HashMap<String,Object>();
		CommonBeanUtils.copyBean2Map(o, model);
		model.put("password", password);
		model.put("seat", seat);
		this.regSendEamil(model);
		*/
		this.dataBaseLogService.log("添加新机构", "机构", "企业名称："+o.getDepartmentName()+"营业执照号："+o.getBussLicenseNo()+",企业编号："+o.getDepartmentId(), "",
				o.getDepartmentName(), userProp);
		return new MessageResponse(0, "添加新机构完成！");
	}
	@Override
	public MessageResponse updateDepartment(Department o, UserProp userProp)
			throws Exception {

		if (CommonUtils.isBlank(o.getDepartmentId())) {
			return new MessageResponse(1, "机构编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getParentDepartmentId())) {
			return new MessageResponse(1, "上级机构编号不能为空！");
		}
		if (CommonUtils.isBlank(o.getDepartmentName())) {
			return new MessageResponse(1, "机构名称不能为空！");
		}
		o.setCreateUserId(userProp.getUserId());
		o.setCreateTime(new Date());
		this.departmentDao.updateDepartmentByPrimaryKey(o);
		this.dataBaseLogService.log("机构信息变更", "机构", "", "企业名称："+o.getDepartmentName()+"营业执照号："+o.getBussLicenseNo()+",企业编号："+o.getDepartmentId(),
				o.getDepartmentName()+","+o.getDepartmentId(), userProp);
		return new MessageResponse(0, "机构信息变更完成！");
	}
	@Override
	public MessageResponse updateDepartmentStautsByPrimaryKey(
			String departmentId, String struts, UserProp userProp)
			throws Exception {
		if (CommonUtils.isBlank(departmentId)) {
			return new MessageResponse(1, "机构编号不能为空！");
		}
		if (CommonUtils.isBlank(struts)) {
			return new MessageResponse(1, "机构状态不能为空！");
		}
		this.departmentDao.updateDepartmentStautsByPrimaryKey(departmentId,
				struts);
		this.dataBaseLogService.log("机构状态变更", "机构状态", "", struts, departmentId,
				userProp);
		return new MessageResponse(0, "审核完成！");
	}
	@Override
	public SingleResult<DepartmentVo> selectDepartmentByPrimaryKey(
			String departmentId) throws Exception {
		SingleResult<DepartmentVo> rst = new SingleResult<DepartmentVo>();
		DepartmentVo departmentVo = this.departmentDao
				.selectDepartmentVoByPrimaryKey(departmentId);
		rst.setValue(departmentVo);
		rst.setStatus(0);
		return rst;
	}
	@Override
	public List<Tree> selectDepartmentTreeList(String id,String syid) throws Exception {

		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.departmentDao.selectDepartmentTreeList(id,syid));
		return commonTreeUtils.getTreeList(id);
	}
	@Override
	public MessageResponse delDepartmentByPrimaryKey(String departmentId,
			UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(departmentId)) {
			return new MessageResponse(1, "机构编号不能为空！");
		}
		this.departmentDao.delDepartmentByPrimaryKey(departmentId);
		this.dataBaseLogService.log("机构删除", "机构", "", "", departmentId,
				userProp);
		return new MessageResponse(0, "机构删除完成！");
	}
	@Override
	public ListResult<Map<String, String>> selectUsersListByDepartmentId(
			String departmentId) throws Exception {
		ListResult<Map<String, String>> rst = new ListResult<Map<String, String>>();
		if (CommonUtils.isBlank(departmentId)) {
			return new ListResult<Map<String, String>>(1, "机构编号不能为空！");
		}
		List<Map<String, String>> list = this.departmentDao
				.selectUsersListByDepartmentId(departmentId);
		rst.setValue(list);
		return rst;
	}

	public void regSendEamil(Map<String,Object> model) throws Exception {
		String subject = "", content = "";
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("resource.loader", "class");
		velocityEngine
				.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		model.put("sysDate", new Date().toLocaleString());
		subject = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"email.subject.reg.vm", "UTF-8", model);
		content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"email.reg.vm", "UTF-8", model);
		Map<String, String> data = new HashMap<String, String>();
		data.put("subject", subject);
		data.put("content", content);
		data.put("to", (String)model.get("contactEmail"));
		this.kafkaProducerService.sendMsg("GESP_SYS_INFO", data);
	}
	@Override
	public MessageResponse importXls(List<Map<String, Object>> list, UserProp userProp) throws Exception {
		int i = 1;
		for (Map<String, Object> row : list) {
			Department o = new Department();
			CommonBeanUtils.copyMap2Bean(o,row);
			o.setCreateTime(new Date());
			o.setCreateUserId(userProp.getUserId());
			o.setStatus("1");
			o.setSyid(userProp.getActiveSyId());
			this.logger.info(o.toString());
			if (CommonUtils.isBlank(o.getDepartmentId())) {
				return new MessageResponse(1,"行"+i+ ",部门编号不能为空！");
			}
			if (CommonUtils.isBlank(o.getParentDepartmentId())) {
				return new MessageResponse(1, "行"+i+ ",上级门编号不能为空！");
			}
			if (CommonUtils.isBlank(o.getDepartmentName())) {
				return new MessageResponse(1, "行"+i+ ",部门名称不能为空！");
			}
			int t = departmentDao.isExit(o);
			if (t > 0) {
				this.departmentDao.updateDepartmentByPrimaryKey(o);
			} else {
				this.departmentDao.insertDepartment(o);
			}
			i++;
		}
		this.dataBaseLogService.log("部门导入", "部门管理", "", "rs.xls",
				"rs.xls", userProp);
		return new MessageResponse(0, "导入完成！");
	}
}
