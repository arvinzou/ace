package com.huacainfo.ace.portal.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.huacainfo.ace.common.model.view.Tree;
import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.tools.CommonUtils;
import com.huacainfo.ace.common.tools.DictUtils;
import com.huacainfo.ace.portal.model.Resources;
import com.huacainfo.ace.portal.service.SystemService;
@Controller
@RequestMapping("/system")
public class SystemController extends PortalBaseController{
	private static final long serialVersionUID = 1L;
	private static final String SESSION_USER_RESOURCES="SESSION_USER_RESOURCES";
	@Autowired
	private SystemService systemService;
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	/**
	 * 
	    * @Title:updatePassword 
	    * @Description:  TODO(更新密码) 
	 		* @param:        @param password
	 		* @param:        @param repassword
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午1:09:59
	 */
	@RequestMapping(value = "/updatePassword.do")
	@ResponseBody
	public MessageResponse updatePassword(String password,String repassword) throws Exception {
		MessageResponse rst=this.systemService.updatePassword(password, repassword, this.getCurUserProp());
		return rst;
	}
	/**
	 * 
	    * @Title:getTreeList 
	    * @Description:  TODO(获取系统操作菜单 ) 
	 		* @param:        @param loadButton
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<Tree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月16日 下午1:10:45
	 */
	@RequestMapping(value = "/getTreeList.do")
	@ResponseBody
	public List<Tree> getTreeList(String loadButton,String client)throws Exception {
		//loadbutton=false;
		boolean lb=Boolean.valueOf(loadButton);
		String rootId="0";
		if(CommonUtils.isNotEmpty(client)){
			Map<String,String> cfg=(Map<String,String>)this.getSession("cfg");
			if(CommonUtils.isNotEmpty(cfg.get("menuRootId"))){
				rootId=cfg.get("menuRootId");
			}
		}
		this.logger.info("=======================rootId:{}",rootId);
		List<Tree> list=this.systemService.getTreeList(this.getSessionUserResources(),rootId,lb);
		return list;
	}
	/**
	 * 
	    * @Title:getSessionUserResources 
	    * @Description:  TODO(获取用户资源) 
	 		* @param:        @return    
	 		* @return:       List<Resources>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:37:13
	 */
	@SuppressWarnings("unchecked")
	private List<Resources> getSessionUserResources(){
		List<Resources> list=null;
		List<Resources> rst=new ArrayList<Resources>();
		Object o=this.getSession(SESSION_USER_RESOURCES);
		if(CommonUtils.isBlank(o)){
			list=this.systemService.getResourcesByUserId(this.getCurUserProp().getUserId());
			this.getRequest().getSession().setAttribute(SESSION_USER_RESOURCES, list);
		}else{
			list=(List<Resources>)o;
		}
		for(Resources e:list){
			if(e.getSyid().equals(this.getCurUserProp().getActiveSyId())||e.getSyid().equals("sys")){
				rst.add(e);
			}
		}
		return list;
	}
	/**
	 * 
	    * @Title:getUrlMap 
	    * @Description:  TODO(获取资源) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ModelAndView    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:37:31
	 */
	@RequestMapping(value = "/getUrlMap.do")
	public ModelAndView getUrlMap()throws Exception {
		List<Resources> list=getSessionUserResources();
		Map<String,String> url=new HashMap<String,String>();
		StringBuffer sb=new StringBuffer("var map=");
		for(Resources resources:list){
			if(resources.getResourcesType().equals("2")){
				url.put(resources.getResourcesId(), resources.getResourcesUrl());
			}
		}
		sb.append(DictUtils.toJsonString(url));
		sb.append(";");
		this.logger.info(sb.toString());
		ModelAndView mav=new ModelAndView("js");
		mav.addObject("js",sb.toString());
		return mav;
	}
	/**
	 * 
	    * @Title:getButtonAuthority 
	    * @Description:  TODO(获取页面按钮权限) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ModelAndView    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:37:41
	 */
	@RequestMapping(value = "/getButtonAuthority.do")
	public ModelAndView getButtonAuthority(String id)throws Exception {
		List<Resources> list=getSessionUserResources();
		Map<String,String> author=this.systemService.getButtonAuthor(list, id);
		StringBuffer sb=new StringBuffer("var authorConfig=");
		sb.append(DictUtils.toJsonString(author));
		sb.append(";");
		
		ModelAndView mav=new ModelAndView("js");
		mav.addObject("js",sb.toString());
		return mav;
	}
	/**
	 * 
	    * @Title:getSessionUser 
	    * @Description:  TODO(获取系统用户信息) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       ModelAndView    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:38:09
	 */
	@RequestMapping(value = "/getUserProp.do")
	public ModelAndView getSessionUser()throws Exception {
		StringBuffer sb=new StringBuffer("var userProp=");
		sb.append(JSON.toJSONString(this.getCurUserProp()));
		sb.append(";");
		ModelAndView mav=new ModelAndView("js");
		mav.addObject("js",sb.toString());
		return mav;
	};
	/**
	 * 
	    * @Title:selectProvinceTreeList 
	    * @Description:  TODO(获取行政区划树) 
	 		* @param:        @param id
	 		* @param:        @param level
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<Tree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:38:29
	 */
	@RequestMapping(value = "/selectProvinceTreeList.do")
	@ResponseBody
	public List<Tree> selectProvinceTreeList(String id,String level)throws Exception {
		boolean isRoot=false;
		if(CommonUtils.isBlank(id)){
			 isRoot=true;
			 if(!CommonUtils.isBlank(this.getCurUserProp())){
				 id=this.getCurUserProp().getAreaCode();
			 }
		}
		if(CommonUtils.isBlank(id)){
			 id="00";
		}
		if(CommonUtils.isBlank(level)){
			return this.systemService.selectProvinceTreeList(id, isRoot,0);
		}
		return this.systemService.selectProvinceTreeList(id, isRoot,Integer.parseInt(level));
	}
	
	/**
	 * 
	    * @Title:selectProvinceTreeList 
	    * @Description:  TODO(获取省) 
	 		* @param:        @param id
	 		* @param:        @param level
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<Tree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:38:29
	 */
	@RequestMapping(value = "/selectProvincesTreeList.do")
	@ResponseBody
	public List<Tree> selectProvincesTreeList(String id,String level)throws Exception {
		boolean isRoot=true;
		if(CommonUtils.isBlank(level)){
			return this.systemService.selectProvinceTreeList(id, isRoot,0);
		}
		return this.systemService.selectProvinceTreeList(id, isRoot,Integer.parseInt(level));
	}
	
	/**
	 * 
	    * @Title:selectDepartment 
	    * @Description:  TODO(机构查询用于控件) 
	 		* @param:        @param q
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       Map<String,Object>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:38:45
	 */
	@RequestMapping(value = "/selectDepartment.do")
	@ResponseBody
	public Map<String,Object> selectDepartment(String q,String id)throws Exception {
		Map<String,String> params=new HashMap<String,String>();
		params.put("q", id);
		if(!CommonUtils.isBlank(q)){
			params.put("q", q);
		}
		return this.systemService.selectDepartment(params,this.getCurUserProp().getActiveSyId());
	}
	/**
	 * 
	    * @Title:loadDictByKey 
	    * @Description:  TODO(加载字典) 
	 		* @param:        @param key
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:39:08
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/loadDictByKey.do")
	@ResponseBody
	public List loadDictByKey(String key)throws Exception {
		@SuppressWarnings("unchecked")
		Map<String, List> kernelMap =(Map<String, List>)this.getRequest().getSession().getServletContext().getAttribute(CommonKeys.dictAppKey);
		return kernelMap.get(key);
	}
	/**
	 * 
	    * @Title:selectUsers 
	    * @Description:  TODO(用户查询用于控件) 
	 		* @param:        @param q
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       Map<String,Object>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:39:33
	 */
	@RequestMapping(value = "/selectUsers.do")
	@ResponseBody
	public Map<String,Object> selectUsers(String q,String id)throws Exception {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("q", id);
		if(!CommonUtils.isBlank(q)){
			params.put("q", q);
		}
		
		this.logger.info("",params);
		return this.systemService.selectUsers(params,this.getCurUserProp().getActiveSyId());
	}
	/**
	 * 
	    * @Title:retrievePassword 
	    * @Description:  TODO(重置密码) 
	 		* @param:        @param email
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:39:49
	 */
	@RequestMapping(value = "/retrievePassword.do")
	@ResponseBody
	public MessageResponse retrievePassword(String email)throws Exception {
		return this.systemService.updateForRetrievePassword(email);
		
	}
	/**
	 * 
	    * @Title:selectDepartmentTreeList 
	    * @Description:  TODO(获取机构树) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<Tree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:39:59
	 */
	@RequestMapping(value = "/selectDepartmentTreeList.do")
	@ResponseBody
	public List<Tree> selectDepartmentTreeList(String id)throws Exception {
		
		List<Tree>	list=this.systemService.selectDepartmentTreeList(this.getCurUserProp().getCorpId(),this.getCurUserProp().getActiveSyId());
		return list;
	}
	/**
	 * 
	    * @Title:selectDepAndUsersTreeList 
	    * @Description:  TODO(获取机构用户树) 
	 		* @param:        @param id
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       List<Tree>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:40:13
	 */
	@RequestMapping(value = "/selectDepAndUsersTreeList.do")
	@ResponseBody
	public List<Tree> selectDepAndUsersTreeList(String id)throws Exception {
		List<Tree>	list=this.systemService.selectDepAndUsersTreeList(id);
		return list;
	}
	/**
	 * 
	    * sendEmailCode 
	    * @Description: 发送验证码 
	 		* @param:        @param email
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       String   
	 		* @throws   
	    
	 */
	@RequestMapping(value = "/sendEmailCode.do")
	@ResponseBody
	public MessageResponse sendEmailCode(String email) throws Exception{
		MessageResponse mess = this.systemService.sendEmailCode(email);
		Map<String, Object> glo = mess.getGloble();
		if(glo!=null){
			this.getRequest().getSession().setAttribute("EmailCode", glo.get("code"));
			this.getRequest().getSession().setAttribute("EmailDate", glo.get("date"));
		}
		return mess;
	}
	/**
	 * 
	    * updateEmail 
	    * @Description: 修改邮箱
	 		* @param:        @param email
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       String   
	 		* @throws   
	    
	 */
	@RequestMapping(value = "/updateEmail.do")
	@ResponseBody
	public MessageResponse updateEmail(String email, String code){
		MessageResponse mess = null;
		String checkCode = (String) getRequest().getSession().getAttribute("EmailCode");
		if(!"".equals(code)&&code.equals(checkCode)){
			mess = this.systemService.updateEmail(email,this.getCurUserProp());
		}
		return mess;
	}
	@RequestMapping(value = "/switchSys.do")
	@ResponseBody
	public MessageResponse switchSys(String syid)throws Exception {
		MessageResponse rst=new MessageResponse();
		for(String e:this.getCurUserProp().getSyid()){
			if(e.equals(syid)){
				this.getCurUserProp().setActiveSyId(syid);
				Map<String,String> cfg=this.systemService.loadConfig(syid);
				this.getRequest().getSession().setAttribute(CommonKeys.cfg, cfg);
				this.getRequest().getSession().setAttribute(CommonKeys.SESSION_USERPROP_KEY, this.getCurUserProp());
				this.systemService.updateCurSyid(syid, this.getCurUserProp().getUserId());
			}
		}
		return rst;
	}
}
