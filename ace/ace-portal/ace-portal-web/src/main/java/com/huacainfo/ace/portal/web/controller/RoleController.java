package com.huacainfo.ace.portal.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.model.view.CheckTree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.common.result.SingleResult;
import com.huacainfo.ace.portal.model.Role;
import com.huacainfo.ace.portal.service.RoleService;
import com.huacainfo.ace.portal.vo.RoleVo;
@Controller
@RequestMapping("/role")
public class RoleController extends PortalBaseController{
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private RoleService roleService;
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
	    * @version: 2016年11月17日 下午2:14:49
	 */
	@RequestMapping(value = "/findRoleList.do")
	@ResponseBody
	public PageResult<RoleVo> findRoleList(Role condition,PageParam page) throws Exception{
		condition.setSyid(this.getCurUserProp().getActiveSyId());
		PageResult<RoleVo> rst = this.roleService.findRoleList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
		return rst;
	}
	/**
	 * 
	    * @Title:insertRole 
	    * @Description:  TODO(添加角色) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:15:02
	 */
	@RequestMapping(value = "/insertRole.do")
	@ResponseBody
	public MessageResponse insertRole(String jsons) throws Exception{
		Role role=JSON.parseObject(jsons, Role.class);
		return   this.roleService.insertRole(role, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:updateRole 
	    * @Description:  TODO(更新角色) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:34:27
	 */
	@RequestMapping(value = "/updateRole.do")
	@ResponseBody
	public MessageResponse updateRole(String jsons) throws Exception{
		Role role=JSON.parseObject(jsons, Role.class);
		return  this.roleService.updateRole(role, this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:insertRoleResources 
	    * @Description:  TODO(角色分配权限) 
	 		* @param:        @param roleId
	 		* @param:        @param resourcesId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:34:41
	 */
	@RequestMapping(value = "/insertRoleResources.do")
	@ResponseBody
	public MessageResponse insertRoleResources(String roleId,
			String resourcesId) throws Exception {
		
		String[] resourcesIds=null;
		String temp=resourcesId;
		if(temp!=null&&temp.indexOf(",")!=-1){
			resourcesIds=temp.split(",");
		}
		if(temp!=null&&temp.indexOf(",")==-1){
			resourcesIds=new String[]{temp};
		}
		return   this.roleService.insertRoleResources(roleId,resourcesIds,this.getCurUserProp());
	}
	/**
	 * 
	    * @Title:selectRoleByPrimaryKey 
	    * @Description:  TODO(根据主键获取角色) 
	 		* @param:        @param roleId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       SingleResult<RoleVo>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:34:58
	 */
	@RequestMapping(value = "/selectRoleByPrimaryKey.do")
	@ResponseBody
	public SingleResult<RoleVo> selectRoleByPrimaryKey(String roleId) throws Exception{
		return   this.roleService.selectRoleByPrimaryKey(roleId);
	}
	/**
	 * 
	    * @Title:selectRoleResourceTreeList 
	    * @Description:  TODO(获取角色权限树) 
	 		* @param:        @param roleId
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<CheckTree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:35:14
	 */
	@RequestMapping(value = "/selectRoleResourceTreeList.do")
	@ResponseBody
	public List<CheckTree> selectRoleResourceTreeList(String roleId)throws Exception {
		List<CheckTree> list=this.roleService.selectRoleResourceTreeList(roleId);
		return list;
	}
	/**
	 * 
	    * @Title:deleteRoleByRoleId 
	    * @Description:  TODO(删除角色) 
	 		* @param:        @param jsons
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:35:44
	 */
	@RequestMapping(value = "/deleteRoleByRoleId.do")
	@ResponseBody
	public MessageResponse deleteRoleByRoleId(String jsons) throws Exception{
		JSONObject json=JSON.parseObject(jsons);
		String roleId=json.getString("id");
		return   this.roleService.deleteRoleByRoleId(roleId,this.getCurUserProp());
	}
	
}
