package com.huacainfo.ace.portal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huacainfo.ace.common.model.UserProp;
import com.huacainfo.ace.common.redis.AspireRedisTemplate;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.SpringUtils;
import com.huacainfo.ace.common.web.tools.WebUtils;
import com.huacainfo.ace.portal.dao.DepartmentDao;
import com.huacainfo.ace.portal.dao.UsersDao;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.DataBaseLogService;
import com.huacainfo.ace.portal.service.ResourcesService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.DepartmentVo;
import com.huacainfo.ace.portal.vo.UsersVo;

@Service("usersService")
public class UsersServiceImpl implements UsersService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private DataBaseLogService dataBaseLogService;
	@Autowired
	private ResourcesService resourceService;
	public PageResult<Map<String, String>> findUsersSearchList(Users condition, int start,
			int limit, String orderBy) throws Exception {
		PageResult<Map<String, String>> rst = new PageResult<Map<String, String>>();
		List<Map<String, String>> list = this.usersDao.findUsersSearchList(
				condition, start, limit, orderBy);
		rst.setRows(list);
		int allRows = this.usersDao.findUsersSearchCount(condition);
		rst.setTotal(allRows);
		return rst;
	}

	public PageResult<UsersVo> findUsersList(Users condition, int start, int limit,
			String orderBy) throws Exception {
		PageResult<UsersVo> rst = new PageResult<UsersVo>();
		List<UsersVo> list = this.usersDao.findUsersList(condition, start,
				start + limit, orderBy);
		rst.setRows(list);
		if (start <= 1) {
			int allRows = this.usersDao.findUsersCount(condition);
			rst.setTotal(allRows);
		}
		return rst;
	}
	
	public MessageResponse insertUsers(Users users, UserProp userProp,String flag)
			throws Exception {
		MessageResponse mr = null;
		if (CommonUtils.isBlank(users.getDepartmentId())) {

			return new MessageResponse(1, "所属部门不能为空!");
		}else{
			DepartmentVo dept=departmentDao.selectDepartmentVoByPrimaryKey(users.getDepartmentId());
			if(CommonUtils.isBlank(dept)){
				return new MessageResponse(1, "所属部门不存在!");
			}
			users.setAreaCode(dept.getAreaCode());
		}
		if(CommonUtils.isBlank(flag)){
			if (CommonUtils.isBlank(users.getAccount())) {

				return new MessageResponse(1, "账户不能为空!");
			}
			if (this.usersDao.isExitUsersAccount(users.getAccount()) > 0) {

				return new MessageResponse(1, "账户已存在!");
			}
			if (CommonUtils.isBlank(users.getPassword())) {

				return new MessageResponse(1, "密码不能为空!");
			}

			if (CommonUtils.isBlank(users.getSex())) {

				return new MessageResponse(1, "性别不能为空!");
			}

			if (CommonUtils.isBlank(users.getEmail())) {

				return new MessageResponse(1, "电子邮箱不能为空!");
			}

			if (!CommonUtils.isValidEmail(users.getEmail())) {

				return new MessageResponse(1, "电子邮箱格式不正确!");
			}
			if (this.usersDao.isExitUsersByEmail(users.getEmail()) > 0) {

				return new MessageResponse(1, "电子邮箱已经被注册使用!");
			}
			users.setStauts("1");
			users.setPassword(CommonUtils.getMd5(users.getPassword()));
			flag="用户";
			mr = new MessageResponse(0,"添加新用户完成,请分配角色！");
		}else{
			flag="联系人";
			mr = new MessageResponse(0,"联系人添加成功！");
		}
		/*
		 * if (CommonUtils.isBlank(users.getIdCard())) {
		 * 
		 * return new SingleResult(false, "身份证号不能为空!"); } if
		 * (!CommonUtils.isValidIdCard(users.getIdCard())) {
		 * 
		 * return new SingleResult(false, "身份证号格式不正确!"); }
		 */
		if (CommonUtils.isBlank(users.getName())) {

			return new MessageResponse(1, "用户名不能为空!");
		}

		if (CommonUtils.isBlank(users.getAreaCode())) {

			return new MessageResponse(1, "所属地区不能为空!");
		}

		if (CommonUtils.isBlank(users.getMobile())) {

			return new MessageResponse(1, "手机号不能为空!");
		}
		if (!CommonUtils.isValidMobile(users.getMobile())) {

			return new MessageResponse(1, "手机号格式不正确!");
		}
		String id=String.valueOf(new Date().getTime());
		users.setUserId(id);
		users.setCreateTime(new Date());
		this.usersDao.insertUsers(users);
		this.logger.debug("", users.toString());
		this.dataBaseLogService.log(flag+"添加成功", flag, "",
				"账号:"+users.getAccount()+",姓名:"+users.getName(), "01", userProp);
		return mr;
	}

	public MessageResponse updateUsers(Users users, UserProp userProp, String flag)
			throws Exception {

		if (CommonUtils.isBlank(users.getUserId())) {

			return new MessageResponse(1, "用户编号不能为空!");
		}

		if (CommonUtils.isBlank(users.getDepartmentId())) {

			return new MessageResponse(1, "所属部门不能为空!");
		}else{
			DepartmentVo dept=departmentDao.selectDepartmentVoByPrimaryKey(users.getDepartmentId());
			if(CommonUtils.isBlank(dept)){
				return new MessageResponse(1, "所属部门不存在!");
			}
			users.setAreaCode(dept.getAreaCode());
		}
		if(CommonUtils.isBlank(flag)){
			if (CommonUtils.isBlank(users.getAccount())) {

				return new MessageResponse(1, "账户不能为空!");
			}

			if (CommonUtils.isBlank(users.getPassword())) {

				return new MessageResponse(1, "密码不能为空!");
			}

			if (CommonUtils.isBlank(users.getSex())) {

				return new MessageResponse(1, "性别不能为空!");
			}
			if (users.getPassword().length() < 20) {
				users.setPassword(CommonUtils.getMd5(users.getPassword()));
			}
			if (CommonUtils.isBlank(users.getEmail())) {

				return new MessageResponse(1, "电子邮箱不能为空!");
			}
			if (!CommonUtils.isValidEmail(users.getEmail())) {

				return new MessageResponse(1, "电子邮箱格式不正确!");
			}
			flag="用户信息变更完成！";
		}else{
			flag="联系人信息修改成功";
		}
		if (CommonUtils.isBlank(users.getName())) {

			return new MessageResponse(1, "用户名不能为空!");
		}

		if (CommonUtils.isBlank(users.getAreaCode())) {

			return new MessageResponse(1, "所属地区不能为空!");
		}

		if (CommonUtils.isBlank(users.getMobile())) {

			return new MessageResponse(1, "手机号不能为空!");
		}
		if (!CommonUtils.isValidMobile(users.getMobile())) {

			return new MessageResponse(1, "手机号格式不正确!");
		}

		Users u = this.selectUsersByPrimaryKey(users.getUserId()).getValue();
		this.usersDao.updateUsersByPrimaryKey(users);
		this.dataBaseLogService.log(flag, "联系人", users.getUserId()+",姓名:"+u.getName()+",手机:"+u.getMobile()+",电话号码:"+u.getTelphone()+",QQ:"+u.getQq()+",传真:"+u.getFax(),
				users.getName()+",姓名:"+users.getName()+",手机:"+users.getMobile()+",电话号码:"+users.getTelphone()+",QQ:"+users.getQq()+",传真:"+users.getFax(), users.getUserId(), userProp);
		return new MessageResponse(0, flag);
	}

	public MessageResponse updateUsersStautsByPrimaryKey(String usersId,
			String struts, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(usersId)) {

			return new MessageResponse(1, "用户编号不能为空！");
		}
		if (CommonUtils.isBlank(usersId)) {

			return new MessageResponse(1, "用户状态不能为空！");
		}
		this.usersDao.updateUsersStautsByPrimaryKey(usersId, struts);
		this.dataBaseLogService.log("用户状态变更", "用户状态", "", struts, usersId,
				userProp);
		return new MessageResponse(0, "用户状态变更完成！");
	}

	public MessageResponse updateUsersForInitPassword(String usersId,
			String password, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(usersId)) {

			return new MessageResponse(1, "用户编号不能为空！");
		}
		if (CommonUtils.isBlank(password)) {

			return new MessageResponse(1, "密码不能为空！");
		}
		password = CommonUtils.getMd5(password);
		this.usersDao.updateUsersForInitPassword(usersId, password);
		this.dataBaseLogService.log("用户初始化密码", "用户密码", "","新密码为："+ password, usersId,
				userProp);
		return new MessageResponse(0, "用户初始化密码完成！");
	}

	public SingleResult<UsersVo> selectUsersByPrimaryKey(String usersId)
			throws Exception {
		SingleResult<UsersVo> rst = new SingleResult<UsersVo>();
		UsersVo usersVo = this.usersDao.selectUsersVoByPrimaryKey(usersId);
		rst.setValue(usersVo);
		return rst;
	}

	public MessageResponse insertUsersRole(String userId, String[] roleId,
			UserProp userProp) throws Exception {
		this.usersDao.insertUsersRole(userId, roleId);
		List<Map<String, String>> list =resourceService.loadResourceDefine();
		AspireRedisTemplate redisTemplateString = (AspireRedisTemplate) SpringUtils
				.getBean("redisTemplateString");
		WebUtils.flushRoleResourceCache(redisTemplateString, list);
		this.dataBaseLogService.log("用户分配角色", "分配角色", "", "", userId, userProp);
		return new MessageResponse(0, "角色分配完成！");
	}

	public PageResult<Role> selectRoleList() throws Exception {
		PageResult<Role> rst = new PageResult<Role>();
		List<Role> list = this.usersDao.selectRoleList();
		rst.setRows(list);
		rst.setTotal(list.size());
		return rst;
	}

	public PageResult<Role> selectRoleListByUserId(String userId) throws Exception {
		PageResult<Role> rst = new PageResult<Role>();
		List<Role> list = this.usersDao.selectRoleListByUserId(userId);
		rst.setRows(list);
		rst.setTotal(list.size());
		return rst;
	}
	
	
	/**
	 * 删除联系人信息
	 * 
	 * @param id
	 * @param userProp
	 * @return MessageResponse
	 * @version: 2017年02月28日 下午16:47
	 */
	public MessageResponse deleteUsers(String id,UserProp userProp) {

		if (CommonUtils.isBlank(id)) {
			return new MessageResponse(1, "编号不能为空！");
		}
		Users u = this.usersDao.selectUsersVoByPrimaryKey(id);
		this.usersDao.deleteUsersById(id);
		this.dataBaseLogService.log("删除联系人信息", u.getName(),
				"用户名:"+u.getName()+",手机号码:"+u.getMobile()+",电话:"+u.getTelphone()+",邮箱:"+u.getEmail()+",QQ:"+u.getQq()+",所属公司:"+u.getDepartmentId()+",数据状态:"+u.getStauts(), 
				"删除编号是"+id, id,
				userProp);
		return  new MessageResponse(0, "删除成功！");
	}

	/**
	 * 
	    * @Title:updateUsersById
	    * @Description:  TODO(修改企业联系人信息) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws 
	    * @version: 2017年02月28日 下午2:43:34
	 */
	public MessageResponse updateUsersById(Users obj, UserProp curUserProp) {
		if (CommonUtils.isBlank(obj.getUserId())) {

			return new MessageResponse(1, "联系人编号不能为空！");
		}
		this.usersDao.updateUsersByPrimaryKey(obj);
		this.dataBaseLogService.log("修改联系人信息", "联系人信息", "", "", obj.getUserId(),
				curUserProp);
		return new MessageResponse(0, "联系人信息修改成功！");
	}
	
	/**
	 * 根据企业编号查询所有联系人
	 * 
	 * @param condition
	 * @param start
	 * @param limit
	 * @param orderBy
	 * @return PageResult<Map<String,String>>
	 */
	public PageResult<Map<String, String>> findDeIdByUsersList(Users condition, int start, int limit, String orderBy) {
		PageResult<Map<String, String>> rst = new PageResult<Map<String, String>>();
		List<Map<String, String>> list = this.usersDao.findDeIdByUsersList(condition, start, limit, orderBy);
		rst.setRows(list);
		int allRows = this.usersDao.findDeIdByUsersCount(condition);
		logger.debug("联系人总数为：{},记录条数为：{}", allRows,list.size());
		rst.setTotal(allRows);
		return rst;
	}
	
	/**
	 * 更新联系人的状态
	 * 
	 * @param id
	 * @param curUserProp
	 * @return
	 */
	public MessageResponse deleteConUsers(String id, UserProp userProp) throws Exception {
		if (CommonUtils.isBlank(id)) {
			return new MessageResponse(1, "编号不能为空！");
		}
		Users u = this.usersDao.selectUsersVoByPrimaryKey(id);
		this.usersDao.updateUsersIdByStatus(id);
		this.dataBaseLogService.log("删除联系人信息", u.getName(),
				"用户名:"+u.getName()+",手机号码:"+u.getMobile()+",电话:"+u.getTelphone()+",邮箱:"+u.getEmail()+",QQ:"+u.getQq()+",所属公司:"+u.getDepartmentId()+",数据状态:"+u.getStauts(), 
				"删除编号是"+id, id,
				userProp);
		return  new MessageResponse(0, "删除成功！");
	}
}
