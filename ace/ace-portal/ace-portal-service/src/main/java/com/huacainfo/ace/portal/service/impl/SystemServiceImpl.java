package com.huacainfo.ace.portal.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.Userinfo;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.HttpUtils;
import com.huacainfo.ace.portal.service.*;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.service.WebContextParamService;
import com.huacainfo.ace.common.tools.CommonBeanUtils;
import com.huacainfo.ace.common.tools.CommonTreeUtils;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.portal.dao.SystemDao;
import com.huacainfo.ace.portal.dao.UsersDao;
import com.huacainfo.ace.portal.dao.UserCfgDao;
import com.huacainfo.ace.portal.dao.UserinfoDao;
import com.huacainfo.ace.portal.model.Config;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.tools.TreeUtils;

@Service("systemService")
public class SystemServiceImpl implements SystemService, WebContextParamService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SystemDao systemDao;
	
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private DataBaseLogService dataBaseLogService;

	@Autowired
	private CacheService cacheService;
	private String key = "C0001";
	@Autowired
	private SysInfoService sysInfoService;
	@Autowired
	private UserCfgDao userCfgDao;
	@Autowired
	private UserinfoDao userinfoDao;

	@Value("#{config[appid]}")
	private String appid;
	@Value("#{config[secret]}")
	private String secret;
	@Value("#{config[redirect_uri]}")
	private String redirect_uri;
	@Value("#{config[scope]}")
	private String scope;
	@Value("#{config[state]}")
	private String state;

	@Autowired
	private OAuth2Service oAuth2Service;

	public List<Resources> getResourcesByUserId(String id) {
		return this.systemDao.selectResourcesByUserId(id, "ace");
	}

	/**
	 *获取菜单目录
	 * @param resources
	 * @param id
	 * @param loadButton
	 * @return
	 */
	public List<Tree> getTreeList(List<Resources> resources, String id, boolean loadButton) {
		TreeUtils treeUtils = new TreeUtils(resources, loadButton);
		return treeUtils.getTreeList(id);
	}

	public Map<String, String> getButtonAuthor(List<Resources> resources,
			String id) {
		Map<String, String> author = new HashMap<String, String>();
		TreeUtils treeUtils = new TreeUtils(resources, true);
		List<Resources> list = treeUtils.getChildResourcesList(id);
		if (list != null) {
			for (Resources r : list) {
				author.put(r.getResourcesUrl(), r.getResourcesName());
			}
		}
		return author;
	}

	@SuppressWarnings("unchecked")
	public List<Tree> selectProvinceTreeList() {
		List<Map<String, Object>> list = (List<Map<String, Object>>) cacheService
				.get(key);
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(list);
		return commonTreeUtils.getTreeList("00");
	}

	public List<Tree> selectProvinceTreeList(String pid, boolean isRoot,
			int level) {

		@SuppressWarnings("unchecked")
        List<Map<String, Object>> list = (List<Map<String, Object>>) cacheService.get(key);
        CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				getAreaListByLevel(list, level));
		if (isRoot) {
			return commonTreeUtils.getTreeListCaseSelf(pid);
		}
		return commonTreeUtils.getTreeList(pid);
	}

	/**
	 * 获取省市区的名称
	 * @param pid
	 * @return String
	 */
	public String selectProvinceNameById(String pid) {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) cacheService.get(key);
		Map<String,Object> resources =null;
		for(Map<String,Object> temp:list){
			if(String.valueOf(temp.get("ID")).equals(pid)){
				resources=temp;
				break;
			}
		}
		return String.valueOf(resources.get("TEXT"));
	}
	private List<Map<String, Object>> getAreaListByLevel(
			List<Map<String, Object>> list, int level) {
		List<Map<String, Object>> rst = new ArrayList<Map<String, Object>>();
		if (level == 0) {
			return list;
		}
		String id="";
		for (Map<String, Object> e : list) {
			id=String.valueOf(e.get("ID"));
			switch (level) {
			case 5:
				if (id.length() == 2) {
					rst.add(e);
				}
				break;
			case 4:
				if (id.length() == 2 || id.length() == 4) {
					rst.add(e);
				}
				break;
			case 3:
				if (id.length() == 2 || id.length() == 4
						|| id.length() == 6) {
					rst.add(e);
				}
				break;
			default:
				break;
			}

		}
		return rst;
	}

	public MessageResponse updatePassword(String password, String repassword,
			UserProp userProp) {

		if (CommonUtils.isBlank(password)) {
			return new MessageResponse(1, "密码不能为空！");
		}
		if (CommonUtils.isBlank(repassword)) {
			return new MessageResponse(1, "确认密码不能为空！");
		}
		if (!repassword.equals(password)) {
			return new MessageResponse(1, "确认密码与密码不一致！");
		}
		password = CommonUtils.getMd5(password);
		this.systemDao.updatePassword(userProp.getUserId(), password);
		this.dataBaseLogService.log("用户密码修改", "用户密码", "", password,
				userProp.getUserId(), userProp);
		return new MessageResponse(0, "密码修改完成！");
	}

	public Map<String, Object> selectDepartment(Map<String, String> params,String syid) {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, String>> list = this.systemDao
				.selectDepartment(params,syid);
		rst.put("total", list.size());
		rst.put("rows", list);
		return rst;
	}

	public Map<String, Object> selectUsers(Map<String, Object> params,String syid) {
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, String>> list = this.systemDao.selectUsers(params,syid);
		rst.put("total", list.size());
		rst.put("rows", list);
		return rst;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.platform.Aspire.service.SystemService#retrievePassword(java.lang.
	 * String)
	 */
	public MessageResponse updateForRetrievePassword(String email) throws Exception {
		if (CommonUtils.isBlank(email)) {
			return new MessageResponse(1, "Email不能为空！");
		}

		Users user = this.systemDao.selectUsersByEmail(email);
		if (user == null) {
			return new MessageResponse(1, "不存在此用户的Email！");
		}
		String newPasswd = CommonUtils.genRandomNum(6);
		this.systemDao.updatePassword(user.getUserId(),
				CommonUtils.getMd5(newPasswd));

		List<String> address = new ArrayList<String>();
		address.add(email);
		
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("resource.loader", "class");
		velocityEngine
				.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		Map<String, Object> model = new HashMap<String, Object>();
		CommonBeanUtils.copyBean2Map(user, model);
		model.put("newPasswd", newPasswd);
		model.put("sysDate", CommonUtils.formatDate(new Date()));
		String subject = CommonUtils.getStringByTemplate("email.subject.vm", model);
		String content = CommonUtils.getStringByTemplate("email.vm", model);
		this.sysInfoService.sendBatchEmail(subject, content, address);
		return new MessageResponse(0, "密码已重新设置，请查收 " + email);
	}

	public List<Tree> selectDepartmentTreeList(String id,String syid) {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				this.systemDao.selectDepartmentTreeList(id,syid));
		return commonTreeUtils.getTreeListCaseSelf(id);
	}

	public Map<String, String> loadConfig(String syid) {
		List<Config> list = this.systemDao.loadConfig(syid);
		Map<String, String> config = new HashMap<String, String>();
		for (Config cfg : list) {
			config.put(cfg.getConfigKey(), cfg.getConfigValue());
		}
		this.logger.info("loadConfig cfg complete");
		return config;
	}

	@SuppressWarnings("unchecked")
	public List<Tree> selectDepAndUsersTreeList(String id) {
		CommonTreeUtils commonTreeUtils = new CommonTreeUtils(
				(List<Map<String, Object>>) cacheService.get("C0002"));
		return commonTreeUtils.getTreeListCaseSelf(id);
	}

	public Users selectUsersByAccount(String account) {
		return this.systemDao.selectUsersByAccount(account);
	}

	public List<Map<String, String>> selectRoleListByUserId(String userId) {
		return this.systemDao.selectRoleListByUserId(userId);
	}

	public List<String> selectRoleTypeListByUserId(String userId) {
		List<String> rst = new ArrayList<String>();
		List<Map<String, String>> list = this.systemDao
				.selectRoleTypeListByUserId(userId);
		for (Map<String, String> o : list) {
			rst.add(o.get("TYPE"));
		}
		return rst;
	}

	
	/**
	 * 发送验证码
	 * 
	 * @param email
	 * @return MessageResponse
	 * @throws MessagingException 
	 */
	public MessageResponse sendEmailCode(String email) {
		MessageResponse mess = new MessageResponse();
		if (CommonUtils.isBlank(email)) {
			mess.setStatus(1);
			mess.setErrorMessage("Email不能为空！");
			mess.setGloble(null);
			return mess;
		}

		Users user = this.systemDao.selectUsersByEmail(email);
		if (user != null) {
			mess.setStatus(1);
			mess.setErrorMessage("该邮箱已存在！");
			mess.setGloble(null);
			return mess;
		}
		String code = CommonUtils.genRandomNum(6);
		String date = CommonUtils.formatDate(new Date());
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("code", code);
		m.put("date", date);
		mess.setGloble(m);
		
		List<String> address = new ArrayList<String>();
		address.add(email);
		
		VelocityEngine velocityEngine = new VelocityEngine();
		velocityEngine.setProperty("resource.loader", "class");
		velocityEngine
				.setProperty("class.resource.loader.class",
						"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		String subject = "修改邮箱";
		String content = "<html><body><div style='font-size:16px'>"+
			    "您的邮箱验证码是<span><strong> "+code+"</strong></span><br>"+
			   " 本Email是系统自动发送，请勿回复！<br>"+date+"</div></body></html>";
		try {
			this.sysInfoService.sendBatchEmail(subject, content, address);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mess.setStatus(0);
		mess.setErrorMessage("验证码已发送，请查收 " + email);
		return mess;
	}
	
	/**
	 * 修改邮箱
	 * 
	 * @param email
	 * @param userProp
	 * @return MessageResponse
	 */
	public MessageResponse updateEmail(String email, UserProp userProp) {
		this.systemDao.updateEmail(email, userProp.getUserId());
		this.dataBaseLogService.log("用户修改邮箱", "用户邮箱", "", email,
				userProp.getUserId(), userProp);
		return new MessageResponse(0, "邮箱修改成功！");
	}
	
	public  String[] selectSyidByUserId(String userId){
		List<Map<String, String>> list = this.systemDao.selectSyidByUserId(userId);
		String [] rst =new String[list.size()];
		int i=0;
		for (Map<String, String> o : list) {
			rst[i]=o.get("syid");
			i++;
		}
		return rst;
	}
	
	 public MessageResponse updateCurSyid(String syid, String userId){
		 this.usersDao.updateCurSyid(syid, userId);
		 return new MessageResponse(0, "成功！");
	 }

	public Map<String,Object> selectUserCfgByUserId(String userId){
		Map<String, Object> cfg = new HashMap<String, Object>();
		List<Map<String,Object>> list=userCfgDao.selectUserCfgByUserId(userId);
		for(Map<String,Object> o:list){
			cfg.put((String)o.get("cfgKey"),o.get("value"));
		}
		return cfg;
	}

	public Map<String, Object> selectUserinfo(Map<String,Object> params)  throws Exception{
		Map<String, Object> rst = new HashMap<String, Object>();
		List<Map<String, Object>> list =this.userinfoDao.getListByDeptId(params);
		rst.put("total", list.size());
		rst.put("rows", list);
		return rst;
	}
	@Override
	public  Users selectUsersByCode(String code,String state) throws Exception{
		this.logger.info("code-> {}", code);
		SingleResult<Userinfo> rst = this.oAuth2Service.saveOrUpdateUserinfo(appid, secret, code, state);
		this.logger.info("===============>unionid-> {}",rst.getValue().getUnionid());
		Users u=this.systemDao.selectUsersByOpenId(rst.getValue().getUnionid());
		this.logger.info("===============>selectUsersByOpenId-> {}",u);
		return u;
	}
}
