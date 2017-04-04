package com.huacainfo.ace.portal.web.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huacainfo.ace.common.model.PageParam;
import com.huacainfo.ace.common.result.PageResult;
import com.huacainfo.ace.portal.service.DataBaseLogService;
@Controller
@RequestMapping("/logs")
public class LogsController extends PortalBaseController{
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DataBaseLogService dataBaseLogService;
	/**
	 * 
	    * @Title:findDictList 
	    * @Description:  TODO(日志分页查询) 
	 		* @param:        @param request
	 		* @param:        @param page
	 		* @param:        @return
	 		* @param:        @throws Exception    
	 		* @return:       PageResult<Map<String,Object>>    
	 		* @throws   
	    * @author: chenxiaoke 
	    * @version: 2016年11月17日 下午1:55:01
	 */
	@RequestMapping(value = "/findList.do")
	@ResponseBody
	public PageResult<Map<String, Object>> findDictList(HttpServletRequest request,PageParam page) throws Exception{
		Map<String, Object> condition = new HashMap<String, Object>();  
		@SuppressWarnings("rawtypes")
		Enumeration e=request.getParameterNames();
		while(e.hasMoreElements()){
			String key=(String)e.nextElement();
			String value=request.getParameter(key);
			condition.put(key, value);
		}
		String dept = this.getCurUserProp().getCorpId();
		condition.put("departmentId", dept);
		if(dept.length()<5){
			condition.put("departmentId", "8");
		}
		PageResult<Map<String, Object>> rst = this.dataBaseLogService.findList(condition,page.getStart(), page.getLimit(), page.getOrderBy());
		if (rst.getTotal() == 0) {
			rst.setTotal(page.getTotalRecord());
		}
	
		return rst;
		
	}
	
	
}
