package com.huacainfo.ace.gesp.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.huacainfo.ace.gesp.model.Users;
import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.kafka.KafkaProducerService;
import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.redis.AspireRedisTemplate;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.common.tools.RegexUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.gesp.dao.RegDao;
import com.huacainfo.ace.gesp.model.Department;
import com.huacainfo.ace.gesp.model.MemberInfo;
import com.huacainfo.ace.gesp.service.MemberInfoService;
import com.huacainfo.ace.gesp.service.RegService;
import com.huacainfo.ace.gesp.model.Users;
import com.huacainfo.ace.portal.service.CacheService;
import com.huacainfo.ace.portal.service.ConfigService;
import com.huacainfo.ace.portal.service.DataBaseLogService;

@Service("regService")
public class RegServiceImpl implements RegService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RegDao regDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private KafkaProducerService kafkaProducerService;
	@Autowired
	private CacheService cacheService;
	@Autowired
	private ConfigService configService;
	@Autowired
	private MemberInfoService memberInfoService;
	
	
	public MessageResponse insertDepartment(Department o, UserProp userProp) throws Exception {
		logger.debug(o.toString());
		o.setRegDate(new Date());
		o.setCreateTime(new Date());
		o.setStatus("0");
		if (CommonUtils.isBlank(o.getParentDepartmentId())) {
			return new MessageResponse(1, "所属协会不能为空！");
		}
		if (CommonUtils.isBlank(o.getDepartmentName())) {
			return new MessageResponse(1, "企业名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getShortName())) {
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
		int t = this.regDao.isExit(o);
		if (t > 0) {
			return new MessageResponse(1, "已存在的企业名称！");
		}
		t = this.regDao.isExitEmail(o.getContactEmail());
		if (t > 0) {
			return new MessageResponse(1, "已存在的联系人邮箱！");
		}
		t = this.regDao.isExitBussLicenseNo(o);
		if (t > 0) {
			return new MessageResponse(1, "已存在的营业执照号！");
		}
		this.regDao.insert(o);
		this.dataBaseLogService.log("添加企业信息", "企业信息", "", ",企业名称："+o.getDepartmentName()+",企业编号:"+o.getDepartmentId(), "",
				userProp);
		String password=CommonUtils.genRandomNum(6);
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
		users.setStauts("0");
		users.setUserLevel("1");
		users.setEmail(o.getContactEmail());
		users.setSeat(seat);
		this.regDao.insertUsersAndRole(users);
		this.dataBaseLogService.log("添加用户信息(企业联系人)", "用户信息", "", users.getUserId(), "",
				userProp);
		configService.insertDeptConfig(o.getDepartmentId());
		Map<String,Object> model=new HashMap<String,Object>();
		CommonBeanUtils.copyBean2Map(o, model);
		model.put("password", password);
		model.put("seat", seat);
		this.regSendEamil(model);
		this.cacheService.put(users.getAccount(), model);
		return new MessageResponse(0, "注册成功！");
	}

	public SingleResult<String> getCheckCode(Map<String,Object> model) throws Exception {
		SingleResult<String> rst = new SingleResult<String>(0,"验证码已经发送到邮箱"+model.get("to"));
		if (CommonUtils.isBlank(model.get("to"))) {
			return new SingleResult<String>(1, "Email不能为空！");
		}
		if(!CommonUtils.isValidEmail(model.get("to").toString())){
			return new SingleResult<>(1, "电子邮箱格式不正确!!");
		}
		int t = this.regDao.isExitEmail((String)model.get("to"));
		if (t > 0) {
			return new SingleResult<String>(1, "已存在的联系人邮箱！");
		}
		String checkCode = CommonUtils.genRandomNum(6);
		rst.setValue(checkCode);
		model.put("sysDate", CommonUtils.formatDate(new Date()));
		model.put("checkCode", checkCode);
		String subject =CommonUtils.getStringByTemplate("email.subject.checkcode.vm", model);
		String content=CommonUtils.getStringByTemplate("email.checkcode.vm", model);
		Map<String, String> data = new HashMap<String, String>();
		data.put("subject", subject);
		data.put("content", content);
		data.put("to", (String)model.get("to"));
		this.kafkaProducerService.sendMsg("GESP_SYS_INFO", data);
		return rst;
	}
	
	public void regSendEamil(Map<String,Object> model) throws Exception {
		model.put("sysDate", CommonUtils.formatDate(new Date()));
		String subject =CommonUtils.getStringByTemplate("email.subject.checkcode.vm", model);
		String content=CommonUtils.getStringByTemplate("email.checkcode.vm", model);
		Map<String, String> data = new HashMap<String, String>();
		data.put("subject", subject);
		data.put("content", content);
		data.put("to", (String)model.get("contactEmail"));
		this.kafkaProducerService.sendMsg("GESP_SYS_INFO", data);
	}
	
	public  MessageResponse sendRegMail(String account, UserProp userProp) throws Exception{
		@SuppressWarnings("unchecked")
		Map<String,Object> model=(Map<String,Object>)this.cacheService.get(account);
		if(CommonUtils.isBlank(model)){
			return new MessageResponse(1, "账号错误！");
		}
		this.regSendEamil(model);
		this.dataBaseLogService.log("给用户发送邮件", "验证码", "", account, "",
				userProp);
		return new MessageResponse(0, "发送成功！");
	}
	public  MessageResponse updateActivateBySeat(String seat, UserProp userProp) throws Exception{
		if(CommonUtils.isBlank(seat)){
			return new MessageResponse(1, "参数错误！");
		}
		int t=this.regDao.updateActivateBySeat(seat);
		if(t<1){
			this.dataBaseLogService.log("激活用户", "用户信息", "", seat, "",
					userProp);
			return new MessageResponse(1, "激活失败！");
		}
		this.dataBaseLogService.log("激活用户", "用户信息", "", seat, "",
				userProp);
		return new MessageResponse(0, "激活成功！");
	}

	/**
	 * 
	 */
	public MessageResponse updateByPrimaryKey(Department o, UserProp userProp)throws Exception  {
		int t=regDao.updateByPrimaryKeySelective(o);
		this.dataBaseLogService.log("修改企业信息", "企业信息", "", o.getDepartmentName(), o.getDepartmentName(),
				userProp);
		if(t<1){
			return new MessageResponse(1, "提交失败！");
		}
		if(o.getBussNum()!=null&&!o.getBussNum().equals("")){
			Users users=new Users();
			users.setAreaCode(o.getAreaCode());
			users.setBirthday(null);
			users.setCreateTime(new Date());
			users.setDepartmentId(o.getDepartmentId());
			users.setIdCard(null);
			users.setMobile(o.getContactMobile());
			users.setName(o.getContactName());
			users.setPassword("");
			users.setStauts("0");
			users.setUserLevel(o.getContactPost());
			users.setEmail(o.getContactEmail());
			users.setTelphone(o.getContactTel());
			users.setFax(o.getContactFax());
			regDao.insertUsers(users);
			this.dataBaseLogService.log("添加用户信息(企业联系人)", "用户信息", "", "用户名称："+users.getName()+",用户编号:"+users.getUserId(), "",
					userProp);
			
			users=new Users();
			users.setAreaCode(o.getAreaCode());
			users.setCreateTime(new Date());
			users.setDepartmentId(o.getDepartmentId());
			users.setIdCard(null);
			users.setMobile(o.getMainTel());
			users.setName(o.getMainPeo());
			users.setPassword("");
			users.setStauts("0");
			users.setUserLevel(o.getMainPost());
			users.setEmail(o.getMainEmail());
			users.setTelphone(o.getMainPhone());
			users.setSeat(UUID.randomUUID().toString());
			this.dataBaseLogService.log("添加企业联系人", "用户信息", "", "用户名称："+users.getName()+",用户编号:"+users.getUserId(), "",
					userProp);
			
			MemberInfo mem = new MemberInfo();
			mem.setCreateDate(new Date());
			mem.setCreateUserId(userProp.getCorpId());
			mem.setCreateUserName(userProp.getCorpName());
			mem.setJoinDate(new Date());
			mem.setEndDate(new Date());
			mem.setStatus("0");;
			mem.setMemberRemark(userProp.getParentCorpId());
			mem.setMemberCode(o.getDepartmentId());
			memberInfoService.insertMemberInfo(mem, userProp);
			this.dataBaseLogService.log("申请成为会员", "会员信息", "", "企业名称："+o.getDepartmentName(), "",
					userProp);
		}
		return new MessageResponse(0, "提交成功！");
	}


	/**
	 * 根据主键查询
	 * @param deptId
	 * @return Department
	 */
	public SingleResult<Department> selectDepartInfoByPrimaryKey(String corpId) {
		SingleResult<Department> rst = new SingleResult<Department>();
		rst.setValue(this.regDao.selectDepartInfoByPrimaryKey(corpId));
		return rst;
	}

	/**
	 * 新增企业
	 * 
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 * @throws Exception 
	 */
	public MessageResponse insert(Department o, UserProp userProp, List<Users> list) throws Exception {
		logger.debug(o.toString());
		o.setRegDate(new Date());
		o.setCreateTime(new Date());
		o.setStatus("0");
		if (CommonUtils.isBlank(o.getParentDepartmentId())) {
			return new MessageResponse(1, "所属协会不能为空！");
		}
		if (CommonUtils.isBlank(o.getDepartmentName())) {
			return new MessageResponse(1, "企业名称不能为空！");
		}
		if (CommonUtils.isBlank(o.getAreaCode())) {
			return new MessageResponse(1, "所属地区不能为空！");
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
		if (CommonUtils.isBlank(o.getNature())) {
			return new MessageResponse(1, "经济性质不能为空！");
		}
		if (CommonUtils.isBlank(o.getBussLicenseNo())) {
			return new MessageResponse(1, "营业执照号不能为空！");
		}
		if (CommonUtils.isBlank(o.getStatus())) {
			return new MessageResponse(1, "状态不能为空！");
		}
		if (CommonUtils.isBlank(o.getCreateTime())) {
			return new MessageResponse(1, "创建时间不能为空！");
		}
		int t = this.regDao.isExit(o);
		if (t > 0) {
			return new MessageResponse(1, "已存在的企业名称！");
		}
		t = this.regDao.isExitBussLicenseNo(o);
		if (t > 0) {
			return new MessageResponse(1, "已存在的营业执照号！");
		}
		if(list.size()<=0){
			return new MessageResponse(1, "请添加联系人！");
		}
		this.regDao.insert(o);
		this.dataBaseLogService.log("添加企业信息", "企业信息", "", "企业名称："+o.getDepartmentName()+",营业执照号："+o.getBussLicenseNo(), o.getDepartmentName(),
				userProp);
		Department de = this.regDao.selectDepartNameById(o.getDepartmentName());
		this.regDao.insertDepartmentSociety(de);
		if(list.size()>0){
			for (Users users : list) {
				if(!CommonUtils.isBlank(users.getEmail())){
					if (!CommonUtils.isValidEmail(users.getEmail())) {

						return new MessageResponse(1, users.getName()+"的电子邮箱格式不正确!");
					}
				}
				if (!CommonUtils.isValidMobile(users.getMobile())) {

					return new MessageResponse(1, users.getName()+"的手机号格式不正确!");
				}
				if(CommonUtils.isBlank(users.getUserLevel())){
					return new MessageResponse(0, users.getName()+"的职务不能为空！");
				}
				Users u=new Users();
				u.setAreaCode(de.getAreaCode());
				u.setCreateTime(new Date());
				u.setDepartmentId(de.getDepartmentId());
				u.setIdCard(null);
				u.setMobile(users.getMobile());
				u.setName(users.getName());
				u.setPassword("");
				u.setStauts("0");
				u.setUserLevel(users.getUserLevel());
				u.setEmail(users.getEmail());
				u.setTelphone(users.getTelphone());
				u.setSeat(UUID.randomUUID().toString());
				regDao.insertUsers(u);
				this.dataBaseLogService.log("添加企业联系人", "联系人信息", "", "联系人姓名："+u.getName()+",手机号:"+u.getMobile()+","+",联系人编号:"+u.getUserId(), de.getDepartmentName(),
						userProp);
			}
		}
		if(!CommonUtils.isBlank(o.getTransBussScope())){
			String[] s = o.getTransBussScope().split(",");
			for (int i = 0; i < s.length; i++) {
				if(!"".equals(s[i])&&s[i]!=null){
					de.setTransBussScope(s[i]);
					this.regDao.insertTransBuss(de);
				}
			}
		}
		
		return new MessageResponse(0, "企业添加成功！");
	}


	/**
	 * 添加企业联系人
	 * 
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 * @throws Exception
	 */
	public MessageResponse insertUsers(Users obj, UserProp userProp) throws Exception {
		if(CommonUtils.isBlank(obj.getUserLevel())){
			return new MessageResponse(0, "职务不能为空！");
		}
		if(CommonUtils.isBlank(obj.getMobile())){
			return new MessageResponse(0, "手机号码不能为空！");
		}
		if(CommonUtils.isBlank(obj.getName())){
			return new MessageResponse(0, "姓名不能为空！");
		}
		if(!CommonUtils.isBlank(obj.getEmail())){
			if (!CommonUtils.isValidEmail(obj.getEmail())) {

				return new MessageResponse(1, "电子邮箱格式不正确!");
			}
		}
		if (!CommonUtils.isValidMobile(obj.getMobile())) {

			return new MessageResponse(1, "手机号格式不正确!");
		}
		Department de = this.regDao.selectDepartInfoByPrimaryKey(obj.getDepartmentId());
		Users u=new Users();
		u.setAreaCode(de.getAreaCode());
		u.setCreateTime(new Date());
		u.setDepartmentId(de.getDepartmentId());
		u.setIdCard("");
		u.setMobile(obj.getMobile());
		u.setName(obj.getName());
		u.setPassword("");
		u.setStauts("0");
		u.setUserLevel(obj.getUserLevel());
		u.setEmail(obj.getEmail());
		u.setTelphone(obj.getTelphone());
		regDao.insertUsers(u);
		this.dataBaseLogService.log("添加企业联系人", "联系人信息", "", "联系人姓名："+u.getName()+",联系人编号:"+u.getUserId(), de.getDepartmentName(),
				userProp);
		return new MessageResponse(0, "企业联系人添加成功！");
	}

	/**
	 * 
	 */
	public Map<String, Object> selectDicBy(String pid, String id) throws Exception {
		return this.regDao.selectDicby(pid, id);
	}

	/**
	 * 将爬取到的企业信息添加到企业信息表中(默认审核通过)
	 * 
	 * 引用(企业注册)
	 * @param obj
	 * @param curUserProp
	 * @return MessageResponse
	 */
	public MessageResponse insertRegDepartment(Department obj, UserProp curUserProp) throws Exception {
		if(CommonUtils.isBlank(obj.getAccount())){
			return new MessageResponse(1, "账号不能为空!");
		}
		if (this.regDao.isExitUsersAccount(obj.getAccount()) > 0) {
			return new MessageResponse(1, "账户已存在!");
		}
		if(CommonUtils.isBlank(obj.getPassword())){
			return new MessageResponse(1, "密码不能为空!");
		}
		if (CommonUtils.isBlank(obj.getDepartmentName())) {
			return new MessageResponse(1, "公司全称不能为空!");
		}
		if (CommonUtils.isBlank(obj.getBussLicenseNo())) {
			return new MessageResponse(1, "工商营业执照号不能为空!");
		}
		if (CommonUtils.isBlank(obj.getTransBussLicenseNo())) {
			return new MessageResponse(1, "道路经营许可正号不能为空!");
		}
		if (CommonUtils.isBlank(obj.getContactName())) {
			return new MessageResponse(1, "注册联系人不能为空!");
		}
		if (CommonUtils.isBlank(obj.getContactMobile())) {
			return new MessageResponse(1, "手机号不能为空!");
		}
		if (!CommonUtils.isValidMobile(obj.getContactMobile())) {
			return new MessageResponse(1, "手机号格式不正确!");
		}
		if (CommonUtils.isBlank(obj.getParentDepartmentId())) {
			return new MessageResponse(1, "所属部门不能为空!");
		}
		//查询爬取到的添加到企业信息表中
		Map<String, Object> b = this.regDao.selectInfoByDepartName(obj.getDepartmentName());
		if(!b.get("fullName").equals(obj.getDepartmentName())){
			return new MessageResponse(1, "请按照营业执照填写公司名称");
		}
		/*if(!b.get("companyLicense").equals(obj.getBussLicenseNo())){
			return new MessageResponse(1, "工商营业执照号有误！");
		}*/
		if(!b.get("businessLicense").equals(obj.getTransBussLicenseNo())){
			return new MessageResponse(1, "请按照道路经营许可证填写道路经营许可证号");
		}
		obj.setTransBussScope(b.get("businessScope")!=null?b.get("businessScope").toString():"");
		obj.setRegAddr(b.get("companyRegAddress")!=null?b.get("companyRegAddress").toString():"");
		obj.setTelphone(b.get("complaintCall")!=null?b.get("complaintCall").toString():"");
		obj.setFax(b.get("fax")!=null?b.get("fax").toString():"");
		obj.setContactTel(b.get("contactCall")!=null?b.get("contactCall").toString():"");
		obj.setLegalPersonName(b.get("legalUserName")!=null?b.get("legalUserName").toString():"");
		obj.setStatus("1");
		obj.setCreateTime(new Date());
		//obj.setCreateUserId(curUserProp.getCorpId());
		obj.setCreateUserName(obj.getContactName());
		this.regDao.insert(obj);
		
		//查询企业信息
		Department de = this.regDao.selectDepartNameById(obj.getDepartmentName());
		Users u=new Users();
		String seat=UUID.randomUUID().toString();
		u.setAreaCode(de.getAreaCode());
		u.setCreateTime(new Date());
		u.setDepartmentId(de.getDepartmentId());
		u.setPassword(CommonUtils.getMd5(obj.getPassword()));
		u.setIdCard("");
		u.setMobile(obj.getContactMobile());
		u.setName(obj.getContactName());
		u.setAccount(obj.getAccount());
		u.setStauts("1");
		u.setUserLevel("2");//注册联系人
		u.setEmail(obj.getContactEmail());
		u.setTelphone(obj.getTelphone());
		u.setSeat(seat);
		regDao.insertUsers(u);
		Users condition = new Users();
		condition.setAccount(u.getAccount());
		condition.setDepartmentId(u.getDepartmentId());
		List<Map<String, String>> ul = this.regDao.findUsersSearchList(condition, 0, 1, "");
		String userId = ul.get(0).get("code");
		String[] roleId = {"9"};
		this.regDao.insertUsersRole(userId, roleId);
		this.regDao.insertDepartmentSociety(de);
		List<Map<String, String>> list =regDao.loadResourceDefine();
		AspireRedisTemplate redisTemplateString = (AspireRedisTemplate) SpringUtils
				.getBean("redisTemplateString");
		WebUtils.flushRoleResourceCache(redisTemplateString, list);
		return new MessageResponse(0, "注册成功");
	}

	/**
	 * 判断账号是否唯一
	 * 
	 * 引用(企业注册)
	 * @param account 账号
	 * @return MessageResponse
	 */
	public MessageResponse isExitAccount(String account) throws Exception {
		int result = this.regDao.isExitUsersAccount(account);
		if(result>0){
			return new MessageResponse(1, "已引用");
		}
		return new MessageResponse(0, "");
	}
	
	/**
	 * 判断该企业是否已注册
	 * 
	 * 引用(企业注册)
	 * @param departmentName 企业全称
	 * @throws Exception
	 * @return
	 */
	public MessageResponse getSiteCompanyInfo(String name) {
		Department result = this.regDao.selectDepartNameById(name);
		if(!CommonUtils.isBlank(result)){
			return new MessageResponse(1, name+"已经注册了");
		}
		Map<String, Object>  companyInfo = get_ph_SiteCompanyInfo(name);
		if (companyInfo == null) {
			companyInfo = get_wxp_SiteCompanyInfo(name);
			if(companyInfo==null)
			{
				return new MessageResponse(1, "请按照营业执照填写公司名称");
			}
	   }
	   if(companyInfo!=null) //先删除,在插入数据库啊 bs_company_info
	   { 
	      this.regDao.deleteByDepartment(name);
	      companyInfo.put("id", UUID.randomUUID().toString());
	      companyInfo.put("datafrom", "9");
	      this.regDao.insertCompanyInfo(companyInfo);
	   }
	   //System.err.println(companyInfo.toString());
	   return new MessageResponse(0, "");
	}


	/**
	 * 普货
	 * 
	 * @param name
	 * @return
	 */
	private Map<String, Object> get_ph_SiteCompanyInfo(String name) {
		Map<String, Object> companyInfo = new HashMap<String, Object>();
		companyInfo.put("id", UUID.randomUUID().toString());

		// 先采集普货,再采集危化品
		String ph_url = "http://58.62.172.116/Query_PHYH.asp?act=Qry&QryID=";
		Map<String, String> params = new HashMap<String, String>();
		params.put("YeHuMingCheng", name);
		params.put("DiZhi", "");
		params.put("JingYingXuKeZhengHao", "");
		params.put("submit", "");
		try {
			String httpPost = null;
			Document doc = Jsoup.parse(httpPost);
			Elements trs = doc.select("table[bordercolorlight='#777777'] tr[class='td2']");
			if (trs != null && trs.size() > 0)// 找到
			{
				Element firstTr = trs.get(0);
				Elements tds = firstTr.select("td");
				if(tds.size()==1)
				{
					if("没有符合条件的数据。".equals( tds.get(0).text())) return null;
				}
				
				companyInfo.put("companyType", "1");
				
				int tds_sizeIndex = 1;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("fullName", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 2;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("businessLicense",
							StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 3;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("economicType", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 4;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("legalUserName", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 5;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("contactCall", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				String id = firstTr.attr("onclick").replace("window.location='Query_PHYH.asp?act=ShowDetail&id=", "")
						.replace("'", "");
				if (RegexUtils.isNumber(id)) {
					companyInfo.put("collectSiteId", id);
					String phDetail_url = "http://58.62.172.116/Query_PHYH.asp?act=ShowDetail&id=" + id;
					String httpsGetDetails = HttpUtils.httpsGet(phDetail_url, 10000, 10000, "gb2312");
					Document detailDoc = Jsoup.parse(httpsGetDetails);
					Elements detailTRs = detailDoc.select("table[bordercolorlight='#777777'] tr[class='td2']");
					if (detailTRs != null && detailTRs.size() > 0) {
						int detailTRs_sizeIndex = 1;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("code", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 4;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("businessScope", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 5;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("bussinessStatus", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 6;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("companyRegCity", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 8;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("companyRegAddress",
										StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 9;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("zipCode", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 10;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("complaintCall", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 11;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("fax", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
					}
				}
				return companyInfo;
			} else // 没找到
			{

				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		 
	}

	/**
	 * 危化品
	 * 
	 * @param name
	 * @return
	 */
	private Map<String, Object> get_wxp_SiteCompanyInfo(String name) {
		Map<String, Object> companyInfo = new HashMap<String, Object>();
		companyInfo.put("id", UUID.randomUUID().toString());

		// 危化品
		String ph_url = "http://58.62.172.116/Query_WXHYYH.asp?act=Qry&QryID=";
		Map<String, String> params = new HashMap<String, String>();
		params.put("YeHuMingCheng", name);
		params.put("DiZhi", "");
		params.put("submit", "");
		try {
			String httpPost = null;
			Document doc = Jsoup.parse(httpPost);
			Elements trs = doc.select("table[bordercolorlight='#777777'] tr[class='td2']");
			if (trs != null && trs.size() > 0)// 找到
			{
				Element firstTr = trs.get(0);
				Elements tds = firstTr.select("td");
				if(tds.size()==1)
				{
					if("没有符合条件的数据。".equals( tds.get(0).text())) return null;
				}
				companyInfo.put("companyType", "2");
				int tds_sizeIndex = 1;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("fullName", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 2;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("businessLicense",
							StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 3;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("economicType", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 4;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("legalUserName", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				tds_sizeIndex = 5;
				if (tds.size() >= tds_sizeIndex)
					companyInfo.put("contactCall", StringEscapeUtils.unescapeHtml(tds.get(tds_sizeIndex - 1).text().replace(" ", "")).trim());
				String id = firstTr.attr("onclick").replace("window.location='Query_WXHYYH.asp?act=ShowDetail&id=", "")
						.replace("'", "");
				if (RegexUtils.isNumber(id)) {
					companyInfo.put("collectSiteId", id);
					String phDetail_url = "http://58.62.172.116/Query_WXHYYH.asp?act=ShowDetail&id=" + id;
					String httpsGetDetails = HttpUtils.httpsGet(phDetail_url, 10000, 10000, "gb2312");
					Document detailDoc = Jsoup.parse(httpsGetDetails);
					Elements detailTRs = detailDoc.select("table[bordercolorlight='#777777'] tr[class='td2']");
					if (detailTRs != null && detailTRs.size() > 0) {
						int detailTRs_sizeIndex = 1;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("code", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 4;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("companyRegAddress", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						
						detailTRs_sizeIndex = 5;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("zipCode", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						
						detailTRs_sizeIndex = 6;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("businessScope", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						 
						detailTRs_sizeIndex = 7;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("complaintCall", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}
						detailTRs_sizeIndex = 8;
						if (detailTRs.size() >= detailTRs_sizeIndex) {
							Elements dtds = detailTRs.get(detailTRs_sizeIndex - 1).select("td");
							if (dtds.size() >= 2)
								companyInfo.put("fax", StringEscapeUtils.unescapeHtml(dtds.get(1).text().replace(" ", "")).trim());
						}

					}
				}
				return companyInfo;
			} else // 没找到
			{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		RegServiceImpl serviceImpl = new RegServiceImpl();
		//serviceImpl.getSiteCompanyInfo("东莞市嘉鑫顺达运输服务有限公司");
		serviceImpl.getSiteCompanyInfo("广州市图腾道路运输有限公司");
	}
}
