package com.huacainfo.ace.portal.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.ExcelUtils;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.model.Users;
import com.huacainfo.ace.portal.service.RoleService;
import com.huacainfo.ace.portal.service.UsersService;
import com.huacainfo.ace.portal.vo.MongoFile;
import com.huacainfo.ace.portal.vo.RoleVo;
import com.huacainfo.ace.portal.vo.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersController extends PortalBaseController {
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UsersService usersService;
	@Autowired
	private RoleService roleService;
	/**
	 * 
	    * @Title:findUsersSearchList 
	    * @Description:  TODO(用户分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Map<String,String>>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:43:00
	 */
	@RequestMapping(value = "/findUsersSearchList.do")
	@ResponseBody
	public PageResult<Map<String, String>> findUsersSearchList(
			Users condition, PageParam page) throws Exception {
		if(CommonUtils.isBlank(condition.getDepartmentId())){
			condition.setDepartmentId(this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String, String>> rst = this.usersService
				.findUsersSearchList(condition,page.getStart(),
						page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	/**
	 * 
	    * @Title:findUsersList 
	    * @Description:  TODO(用户分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<UsersVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:43:12
	 */
	@RequestMapping(value = "/findUsersList.do")
	@ResponseBody
	public PageResult<UsersVo> findUsersList(Users condition, PageParam page)
			throws Exception {
		/*if(CommonUtils.isBlank(condition.getAreaCode())){
			int corpId = this.getCurUserProp().getCorpId().length();
			if(corpId>6){
				condition.setAreaCode(this.getCurUserProp().getAreaCode());
			}
		}
		if(CommonUtils.isBlank(condition.getDepartmentId())){
			condition.setDepartmentId(this.getCurUserProp().getCorpId());
		}*/
		PageResult<UsersVo> rst = this.usersService.findUsersList(condition,
				page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;

	}
	/**
	 * 
	    * @Title:findDeIdByUsersList 
	    * @Description:  TODO(用户分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<UsersVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:43:12
	 */
	@RequestMapping(value = "/findDeIdByUsersList.do")
	@ResponseBody
	public PageResult<Map<String, String>> findDeIdByUsersList (Users condition, PageParam page)
			throws Exception {
		if(CommonUtils.isBlank(condition.getDepartmentId())){
			condition.setDepartmentId(this.getCurUserProp().getCorpId());
		}
		PageResult<Map<String, String>> rst = this.usersService.findDeIdByUsersList(condition,
				page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		 
		return rst;
	}
	/**
	 * 
	    * @Title:insertUsers 
	    * @Description:  TODO(添加用户) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:43:24
	 */
	@RequestMapping(value = "/insertUsers.do")
	@ResponseBody
	public MessageResponse insertUsers(String jsons,String flag) throws Exception {

		Users obj = JSON.parseObject(jsons, Users.class);
		return this.usersService.insertUsers(obj, this.getCurUserProp(),flag);

	}
	/**
	 * 
	    * @Title:updateUsers 
	    * @Description:  TODO(更新用户) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:43:34
	 */
	@RequestMapping(value = "/updateUsers.do")
	@ResponseBody
	public MessageResponse updateUsers(String jsons,String flag) throws Exception {

		Users obj = JSON.parseObject(jsons, Users.class);
		obj.setUserId(obj.getId());
		return this.usersService.updateUsers(obj, this.getCurUserProp(), flag);
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
	    * @author: wuxiaoyu
	    * @version: 2017年02月28日 下午2:43:34
	 */
	@RequestMapping(value = "/updateUsersById.do")
	@ResponseBody
	public MessageResponse updateUsersById(String jsons) throws Exception {
		Users obj = JSON.parseObject(jsons, Users.class);
		return this.usersService.updateUsersById(obj, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateUsers 
	    * @Description:  TODO(删除用户) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:43:34
	 */
	@RequestMapping(value = "/deleteUsers.do")
	@ResponseBody
	public MessageResponse deleteUsers(String id) throws Exception {
		return this.usersService.deleteUsers(id,this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateUsersStautsByPrimaryKey 
	    * @Description:  TODO(更新用户状态) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:43:48
	 */
	@RequestMapping(value = "/updateUsersStautsByPrimaryKey.do")
	@ResponseBody
	public MessageResponse updateUsersStautsByPrimaryKey(String jsons)
			throws Exception {

		JSONObject json = JSON.parseObject(jsons);
		String userId = json.getString("id");
		String stauts = "0";
		if (json.containsKey("stauts")) {
			stauts = json.getString("stauts");
		}
		return this.usersService.updateUsersStautsByPrimaryKey(userId, stauts,
				this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateUsersForInitPassword 
	    * @Description:  TODO(初始化用户密码) 
	 		* @param:        @param userId
	 		* @param:        @param password
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:44:00
	 */
	@RequestMapping(value = "/updateUsersForInitPassword.do")
	@ResponseBody
	public MessageResponse updateUsersForInitPassword(String userId,
			String password) throws Exception {

		return this.usersService.updateUsersForInitPassword(userId, password,
				this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectUsersByPrimaryKey 
	    * @Description:  TODO(根据主键获取用户信息) 
	 		* @param:        @param userId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<UsersVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:44:15
	 */
	@RequestMapping(value = "/selectUsersByPrimaryKey.do")
	@ResponseBody
	public SingleResult<UsersVo> selectUsersByPrimaryKey(String userId)
			throws Exception {
		return this.usersService.selectUsersByPrimaryKey(userId);
	}
	/**
	 * 
	    * @Title:insertUsersRole 
	    * @Description:  TODO(分配角色) 
	 		* @param:        @param userId
	 		* @param:        @param roleId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:44:41
	 */
	@RequestMapping(value = "/insertUsersRole.do")
	@ResponseBody
	public MessageResponse insertUsersRole(String userId, String roleId)
			throws Exception {

		String[] roleIds = null;
		String temp = roleId;
		if (temp != null && temp.indexOf(",") != -1) {
			roleIds = temp.split(",");
		}
		if (temp != null && temp.indexOf(",") == -1) {
			roleIds = new String[] { temp };
		}
		return this.usersService.insertUsersRole(userId, roleIds,
				this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectRoleList 
	    * @Description:  TODO(获取角色列表) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Role>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:44:58
	 */
	@RequestMapping(value = "/selectRoleList.do")
	@ResponseBody
	public PageResult<Role> selectRoleList() throws Exception {
		return this.usersService.selectRoleList();
	}
	/**
	 * 
	    * @Title:selectRoleListByUserId 
	    * @Description:  TODO(获取已分配角色列表) 
	 		* @param:        @param userId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Role>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:45:16
	 */
	@RequestMapping(value = "/selectRoleListByUserId.do")
	@ResponseBody
	public PageResult<Role> selectRoleListByUserId(String userId)
			throws Exception {
		return this.usersService.selectRoleListByUserId(userId);
	}
	/**
	 * 
	    * @Title:findRoleList 
	    * @Description:  TODO(角色分页查询) 
	 		* @param:        @param condition
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<RoleVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:45:32
	 */
	@RequestMapping(value = "/findRoleList.do")
	@ResponseBody
	public PageResult<RoleVo> findRoleList(Role condition, PageParam page)
			throws Exception {
		PageResult<RoleVo> rst = this.roleService.findRoleList(condition,
				page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	

	@RequestMapping(value = "/deleteConUsers.do")
	@ResponseBody
	public MessageResponse deleteConUsers(String id)throws Exception {
		MessageResponse me = this.usersService.deleteConUsers(id,this.getCurUserProp());
		return me;
	}
	@RequestMapping(value = "/importXls.do")
	@ResponseBody
	public MessageResponse importXls(@RequestParam MultipartFile[] file,
									 String jsons) throws Exception {
		ExcelUtils utils = new ExcelUtils();
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		MongoFile[] files = new MongoFile[file.length];
		int i = 0;
		for (MultipartFile o : file) {
			MongoFile obj = new MongoFile();
			obj.setInputStream(o.getInputStream());
			obj.setFilename(o.getOriginalFilename());
			obj.setLength(o.getSize());
			files[i] = obj;
			i++;
			String ext = obj
					.getFilename()
					.toLowerCase()
					.substring(
							obj.getFilename().toLowerCase()
									.lastIndexOf("."));
			this.logger.info(ext);
			if (ext.equals(".xls")) {
				list = utils.readExcelByJXL(obj.getInputStream(), 1);
			}
			if (ext.equals(".xlsx")) {
				list = utils.readExcelByPOI(obj.getInputStream(), 1);
			}
		}
		return this.usersService.importXls(list, this.getCurUserProp());
	}
}
