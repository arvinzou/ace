package com.huacainfo.ace.portal.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.result.MessageResponse;
import com.huacainfo.ace.common.tools.CommonKeys;
import com.huacainfo.ace.common.web.controller.BaseController;
import com.huacainfo.ace.portal.service.CacheService;
import com.huacainfo.ace.portal.service.SystemService;
@Controller
@RequestMapping("/sysConfig")
public class SysConfigController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private SystemService systemService;
	
	@Autowired
	private CacheService cacheService;
	/**
	 * 
	    * @Title:deployConfig 
	    * @Description:  TODO(发布系统参数) 
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       MessageResponse    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午2:36:07
	 */
	@RequestMapping(value = "/deployConfig.do")
	@ResponseBody
	public  MessageResponse deployConfig() throws Exception{
		this.logger.info("loadConfig cfg complete");
		cacheService.init();
		this.getRequest().getSession().getServletContext().setAttribute(CommonKeys.cfg, this.systemService.loadConfig(this.getCurUserProp().getActiveSyId()));
		return new MessageResponse(0,"发布成功！");
	}
}
